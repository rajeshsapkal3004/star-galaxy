package com.cartwheel.galaxy.service.impl;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cartwheel.galaxy.entity.User;
import com.cartwheel.galaxy.repository.UserRepository;
import com.cartwheel.galaxy.service.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User saveUser = null;
		if (Objects.isNull(user)) {

		} else {
			saveUser = userRepository.save(user);
		}
		return saveUser;
	}

	@Override
	public User findByEmail(String email) {
		User user= null;
		if(email!=null){
			user=userRepository.findByEmailIgnoreCase(email);
		}
		return user;
	}


}
