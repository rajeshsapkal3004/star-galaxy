package com.cartwheel.galaxy.controller;

import com.cartwheel.galaxy.businessConstant.UserConstant;
import com.cartwheel.galaxy.commonMapper.Mapper;
import com.cartwheel.galaxy.config.JwtUtils;
import com.cartwheel.galaxy.dto.*;
import com.cartwheel.galaxy.entity.ValidationTokenForUser;
import com.cartwheel.galaxy.service.impl.EmailService;
import com.cartwheel.galaxy.service.impl.UserDetailsServiceImpl;
import com.cartwheel.galaxy.service.impl.UserValidationTokenService;
import com.cartwheel.galaxy.service.impl.ValidationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cartwheel.galaxy.entity.User;
import com.cartwheel.galaxy.repository.UserRepository;
import com.cartwheel.galaxy.service.CreateUserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/star-galaxy")
public class MainController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CreateUserService createUserService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConstant userConstant;

	@Autowired
	private Mapper mapper;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ValidationToken validationToken;

	@Autowired
	private UserValidationTokenService userValidationTokenService;

	@PostMapping("/create-user")
	public ResponseEntity<?> createUser(@RequestBody UserDto userdto) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			//check if email is already taken
			if (createUserService.findByEmail(userdto.getEmail()) != null) {
				jsonResponse.setResponseMessage("Email is already Exist");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
			}

			userdto.setUserRole(userConstant.DEFAULT_ROLE);
			String encodepwd = bCryptPasswordEncoder.encode(userdto.getPassword());
			userdto.setPassword(encodepwd);
			User convertUserDtoToUser = mapper.convertUserDtoToUser(userdto);
			User createUser = createUserService.createUser(convertUserDtoToUser);

			if (createUser != null) {
				//create token for user
				ValidationTokenForUser validationTokenForUser = new ValidationTokenForUser(createUser);
				validationToken.createToken(validationTokenForUser);
				EmailBody emailBody = new EmailBody();
				emailBody.setTo(createUser.getEmail());
				emailBody.setSubject("Please Complete Account Validation");
				emailBody.setMessage("http://localhost:8080/star-galaxy/confirm-account/" + validationTokenForUser.getConfirmationToken());
				boolean flagForSuccssfullySentEmail = emailService.sendMail(emailBody);
				if (flagForSuccssfullySentEmail) {
					jsonResponse.setResponseMessage("Account Created!, Verification mail Sent please verify account");
					return ResponseEntity.ok().body(jsonResponse);
				} else {
					jsonResponse.setResponseMessage("Failed to send email for account validation");
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
				}
			} else {
				jsonResponse.setResponseMessage("Failed to create user.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
			}
		} catch (Exception e) {
			// Log the exception for further analysis
			// You might want to log the details of the exception, or use a logging framework like SLF4J
			jsonResponse.setResponseMessage("An unexpected error occurred");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
		}
	}


	//handales user validation
	@GetMapping("/confirm-account/{token}")
	public ResponseEntity<?> confirmUserValidation(@PathVariable String token){
		ValidationTokenForUser userAssociateToken = userValidationTokenService.findToken(token);
		User userByEmail=null;
		if(token != null){
			userByEmail = createUserService.findByEmail(userAssociateToken.getUser().getEmail());
			userByEmail.setActive(true);
			createUserService.createUser(userByEmail);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("User: "+userByEmail.getEmail()+" has been Validated");
	}

	//generate token for user login
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequestDto jwtRequestDto) throws Exception{
		try{
			authenticate(jwtRequestDto.getUsername(),jwtRequestDto.getPassword());
		}catch (UsernameNotFoundException e){
			throw new Exception("User Not Found");
		}
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequestDto.getUsername());
		String generatedToken = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponseDto(generatedToken));
	}

	// if loggedin user is admin = admin or modrate user
	// if loggedin user is moderate = modrate user
	@GetMapping("/access/{userId}/{userRole}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
		User user = userRepository.findById(userId);
		List<String> activeRole = getRoleByLoggedUser(principal);
		String newRole = "";
		if (activeRole.contains(userRole)) {
			newRole =  user.getUserRole() + "," + userRole;
			user.setUserRole(newRole);
		}
		userRepository.save(user);
		return "Hi "+user.getUserName()+"New Role assign to you by" +principal.getName();
	}

	@GetMapping
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> loadUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public  String testUserAccess(){
		return "user can only access this!";
	}

	private List<String> getRoleByLoggedUser(Principal principal) {
		String roles = getLoggedinUser(principal).getUserRole();
		List<String> assigneRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assigneRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(userConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if (assigneRoles.contains("ROLE_MODERATOR")) {
			return Arrays.stream(userConstant.MODERATOR_ACCESS).collect(Collectors.toList());
		}
		return Collections.emptyList();

	}

	// help to find who is a loggedin user
	private User getLoggedinUser(Principal principal) {
		return userRepository.findByUserName(principal.getName()).get();
	}

	private void authenticate(String username, String password) throws Exception {
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}catch (DisabledException e){
			throw new Exception("User Disabled"+e.getMessage());
		}
		catch(BadCredentialsException e)
		{
			System.out.println("Invalid Credentials"+e.getMessage());
		}
	}

	@GetMapping("/current-user")
	public ResponseEntity<?> getCurrentUser(Principal principal) {
		try {
			// Retrieve the logged-in user's details using the principal
			User currentUser = getLoggedinUser(principal);
			// Return the current user's details
			return ResponseEntity.ok(currentUser);
		} catch (Exception e) {
			// Log the exception for further analysis
			// You might want to log the details of the exception, or use a logging framework like SLF4J
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
		}
	}

}
