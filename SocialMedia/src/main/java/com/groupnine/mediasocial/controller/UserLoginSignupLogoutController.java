package com.groupnine.mediasocial.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.groupnine.mediasocial.entity.ERole;
//import com.groupnine.mediasocial.entity.Role;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.payload.request.LoginRequest;
import com.groupnine.mediasocial.payload.request.SignupRequest;
import com.groupnine.mediasocial.payload.response.JwtResponse;
import com.groupnine.mediasocial.payload.response.MessageResponse;
//import com.groupnine.mediasocial.repository.RoleRepository;
import com.groupnine.mediasocial.repository.UserRepository;
import com.groupnine.mediasocial.security.jwt.JwtUtils;
import com.groupnine.mediasocial.service.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserLoginSignupLogoutController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Optional<User> checkLogin = userRepository.findByGmail(loginRequest.getGmail());
		if (checkLogin.isPresent()) {
			User user = checkLogin.get();
			if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getGmail(), loginRequest.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtUtils.generateJwtToken(authentication);

				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
				return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUserid(), userDetails.getGmail(),
						userDetails.getProfile_name()));
			} else {
				// Mật khẩu không đúng
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Mật khẩu không đúng!"));
			}
		} else {
			// Tài khoản không tồn tại
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Tài khoản không tồn tại!"));
		}

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		Optional<User> userCheck = userRepository.existsByGmail(signUpRequest.getGmail());
		if (!userCheck.isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Gmail này đã tồn tại!"));
		}

//	    if (userRepository.existsByEmail(signUpRequest.getProfile_name())) {
//	      return ResponseEntity
//	          .badRequest()
//	          .body(new MessageResponse("Error: Profile name is already in use!"));
//	    }
		LocalDate signupDate = LocalDate.now(); // Ngày đăng ký hiện tại

		// Create new user's account
		User user = new User(signUpRequest.getGmail(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getProfileName(), signupDate);


		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Đăng ký thành công!"));
	}
}
