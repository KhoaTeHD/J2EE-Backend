package com.groupnine.mediasocial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.repository.UserRepository;

@Service
public class UserUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.groupnine.mediasocial.entity.User> opt = userRepository.findByGmail(username);		
		
		if(opt.isPresent()) {
			com.groupnine.mediasocial.entity.User user = opt.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			
			
			
			return new User(user.getGmail(),user.getPassword(),authorities);
		}
		
		throw new BadCredentialsException("user not fund with username" + username);
	}

}
