package com.cartwheel.galaxy.controller;

import com.cartwheel.galaxy.businessConstant.UserConstant;
import com.cartwheel.galaxy.commonMapper.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cartwheel.galaxy.dto.UserDto;
import com.cartwheel.galaxy.entity.User;
import com.cartwheel.galaxy.repository.UserRepository;
import com.cartwheel.galaxy.service.CreateUserService;
import com.cartwheel.galaxy.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;

@RestController
@CrossOrigin("*")
@RequestMapping("/star-galaxy")
public class MainController {

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

	@PostMapping("/create-user")
	public ResponseEntity<?> createUser(@RequestBody UserDto userdto) {
		userdto.setUserRole(userConstant.DEFAULT_ROLE);
		String encodepwd = bCryptPasswordEncoder.encode(userdto.getPassword());
		userdto.setPassword(encodepwd);
		User convertUserDtoToUser = mapper.convertUserDtoToUser(userdto);
		User createUser = createUserService.createUser(convertUserDtoToUser);
		if (createUser != null) {
			return ResponseEntity.ok(createUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
		}
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
}
