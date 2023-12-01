package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.payload.response.MessageResponse;
import com.groupnine.mediasocial.service.FriendListService;
import com.groupnine.mediasocial.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/friends")
public class FriendListController {
	
	@Autowired
	private FriendListService friendservice;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<User>> getFriendsById(@PathVariable Long userId) throws UserException{
		User user = userService.findUserById(userId);
		
		for (User u : user.getFriends()) {
			u.setFriends(null);
		}
		
		return new ResponseEntity<List<User>>(user.getFriends(),HttpStatus.OK);
	}
	
	@GetMapping("/mutualfriends/{userIds}")
	public int getMutualFriendsHandler(@PathVariable List<Long> userIds) throws UserException{
		
		int result = userService.getMutualFriends(userIds.get(0), userIds.get(1));
		
		return result;
	}
	
	@DeleteMapping("/unfriend/{userId}/{friendId}")
	public ResponseEntity<?> Unfriend(@PathVariable Long userId,@PathVariable Long friendId) {
		boolean check = friendservice.Unfriend(userId, friendId);
		
		if(check) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Có lỗi xảy ra vui lòng thử lại!"));
	}
	
	@GetMapping("/recommend/{userId}")
	public ResponseEntity<List<User>> getRecommendFriends(@PathVariable Long userId) throws UserException{
		List<User> recommend = userService.findFriendForId(userId);
		
		for (User u : recommend) {
			u.setFriends(null);
			u.setSentFriendRequest(null);
			u.setReceivedFriendRequest(null);
		}
		
		return new ResponseEntity<List<User>>(recommend,HttpStatus.OK);
	}
}
