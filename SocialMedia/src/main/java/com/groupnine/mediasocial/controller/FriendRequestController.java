package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.groupnine.mediasocial.entity.FriendRequest;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.payload.response.MessageResponse;
import com.groupnine.mediasocial.service.FriendRequestService;
import com.groupnine.mediasocial.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/requests")
public class FriendRequestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendRequestService frService;
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<FriendRequest>> getRequests(@PathVariable Long userId) throws UserException{
		User user = userService.findUserById(userId);
		
		List<FriendRequest> requestList = user.getReceivedFriendRequest();
		
		for( FriendRequest fr : requestList) {
			fr.getSender().setSentFriendRequest(null);
			fr.getSender().setFriends(null);
			fr.getSender().setReceivedFriendRequest(null);
			fr.setReceiver(null);
		}
		
		return new ResponseEntity<List<FriendRequest>>(user.getReceivedFriendRequest(), HttpStatus.OK);
	}
	
	@PostMapping("/add/{senderId}/{receiverId}")
	public ResponseEntity<?> addRequest(@PathVariable Long senderId,@PathVariable Long receiverId) throws UserException{
		boolean check = frService.saveRequest(senderId, receiverId);
		
		if(check) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Có lỗi xảy ra vui lòng thử lại!"));
	}
	
	@PostMapping("/accept/{receiverId}/{senderId}")
	public ResponseEntity<?> acceptRequest(@PathVariable Long senderId,@PathVariable Long receiverId) throws UserException{
		boolean check = frService.acceptRequest(senderId, receiverId);
		
		if(check) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Có lỗi xảy ra vui lòng thử lại!"));
	}
	
	@DeleteMapping("/deny/{receiverId}/{senderId}")
	public ResponseEntity<?> denyRequest(@PathVariable Long senderId,@PathVariable Long receiverId) throws UserException{
		boolean check = frService.denyRequest(senderId, receiverId);
		
		if(check) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Có lỗi xảy ra vui lòng thử lại!"));
	}
}
