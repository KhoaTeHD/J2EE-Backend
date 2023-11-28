package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.groupnine.mediasocial.entity.FriendRequest;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/requests")
public class FriendRequestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<FriendRequest>> getRequests(@PathVariable Long userId) throws UserException{
		User user = userService.findUserById(userId);
		
		return new ResponseEntity<List<FriendRequest>>(user.getReceivedFriendRequest(), HttpStatus.OK);
	}
	
	
}
