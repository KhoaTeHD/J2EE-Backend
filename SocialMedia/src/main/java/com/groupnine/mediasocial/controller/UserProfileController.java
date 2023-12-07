package com.groupnine.mediasocial.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.payload.request.UserChangePasswordRequest;
import com.groupnine.mediasocial.payload.request.UserChangeProfileRequest;
import com.groupnine.mediasocial.service.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.criteria.Path;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/userProfile/")
public class UserProfileController {
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		user.setFriends(null);
		user.setSentFriendRequest(null);
		user.setComments(null);
		user.setReceivedFriendRequest(null);
		user.setShared(null);
		user.setLikes(null);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("numFriends/{id}")
	public int getNumFriends(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		
		return user.getFriends().size();
	}
	
	@PostMapping("/updateProfile/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserChangeProfileRequest updateRequest, @PathVariable Long id) throws UserException{
		User currentUser = userService.findUserById(id);
		User updateUser = new User();
		updateUser.setUserId(updateRequest.getUserId());
		updateUser.setProfileName(updateRequest.getProfileName());
		updateUser.setBirthday(updateRequest.getBirthday());
		updateUser.setBiography(updateRequest.getBiography());
		updateUser.setGender(updateRequest.getGender());
		updateUser.setAvatar(updateRequest.getAvatar());
		User resultUser = userService.updateProfile(updateUser, currentUser);
		if(resultUser != null) {
			return new ResponseEntity<> ("Cập nhật thông tin người dùng thành công" ,HttpStatus.OK);
		}
		return new ResponseEntity<> ("Update thất bại", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/updatePassword/{id}")
	public ResponseEntity<?> updatePassword(@RequestBody UserChangePasswordRequest updateRequest, @PathVariable Long id) throws UserException{
		User currentUser = userService.findUserById(id);
		
		if(!encoder.matches(updateRequest.getOld_password(), currentUser.getPassword())) {
			return new ResponseEntity<> ("101", HttpStatus.OK);
		} else if(updateRequest.getOld_password().endsWith(updateRequest.getNew_password())) {
			return new ResponseEntity<> ("102", HttpStatus.OK);
		} else {
			currentUser.setPassword(encoder.encode(updateRequest.getNew_password()));
			int result = userService.updatePassword(currentUser);
			return result == 1 ? new ResponseEntity<> ("103", HttpStatus.OK) : new ResponseEntity<> ("104", HttpStatus.OK);
		}
	}
	
	@GetMapping("numPost/{id}")
	public int getNumPost(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		
		return user.getPosts().size();
	}

}
