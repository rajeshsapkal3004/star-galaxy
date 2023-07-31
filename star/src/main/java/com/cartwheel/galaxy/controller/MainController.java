package com.cartwheel.galaxy.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/star-galaxy")
public class MainController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CreateUserService createUserService;

	@Autowired
	private UserRepository userRepository;

	public static final String DEFAULT_ROLE = "ROLE_USER";
	public static final String[] ADMIN_ACCESS = { "ROLE_ADMIN", "ROLE_MODERATOR" };
	public static final String[] MODERATOR_ACCESS = { "ROLE_MODERATOR" };

	@PostMapping("/create-user")
	public ResponseEntity<?> createUser(@RequestBody UserDto userdto) {
		userdto.setUserRole(DEFAULT_ROLE);
		String encodepwd = bCryptPasswordEncoder.encode(userdto.getPassword());
		userdto.setPassword(encodepwd);
		User convertUserDtoToUser = convertUserDtoToUser(userdto);
		User createUser = createUserService.createUser(convertUserDtoToUser);

		if (createUser != null) {
			return ResponseEntity.ok(createUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
		}

	}

	// if loggedin user is admin = admin or modrate user
	// if loggedin user is moderate = modrate user

	@GetMapping("/access/{userId}/{userRole}	``																																																																																						")
	public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
		Optional<User> user = userRepository.findById(userId);
		List<String> activeRole = getRoleByLoggedUser(principal);
		String newRole = "";
		if (activeRole.contains(userRole)) {
			user.getUserRole()
			
		}
		
	}

	private List<String> getRoleByLoggedUser(Principal principal) {
		String roles = getLoggedinUser(principal).getUserRole();
		List<String> assigneRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assigneRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(ADMIN_ACCESS).collect(Collectors.toList());
		}
		if (assigneRoles.contains("ROLE_MODERATOR")) {
			return Arrays.stream(MODERATOR_ACCESS).collect(Collectors.toList());
		}
		return Collections.emptyList();

	}

	// help to find who is a loggedin user
	private User getLoggedinUser(Principal principal) {
		return userRepository.findByUserName(principal.getName()).get();
	}

	private User convertUserDtoToUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}

}
