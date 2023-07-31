package com.cartwheel.galaxy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cartwheel.galaxy.entity.User;
import com.cartwheel.galaxy.repository.UserRepository;
import com.cartwheel.galaxy.service.HomeService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUserName(username);
		return user.map(UserServiceImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found!! " + username));
	}
}
