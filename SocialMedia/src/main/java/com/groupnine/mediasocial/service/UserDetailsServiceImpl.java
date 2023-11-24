package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
    User user = userRepository.findByGmail(gmail)
        .orElseThrow(() -> new UsernameNotFoundException("Không tồn tại gmail này: " + gmail));
    return UserDetailsImpl.build(user);
  }

}
