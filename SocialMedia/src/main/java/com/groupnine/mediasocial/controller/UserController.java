package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController { 
	@Autowired
	private UserService userService;

	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		user.setFriends(null);
		user.setReceivedFriendRequest(null);
		user.setSentFriendRequest(null);
		user.setLikes(null);
		user.setComments(null);
		user.setShared(null);
		user.setPosts(null);
		user.setChat(null);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException{
		List<User> users = userService.searchUser(query);
		
		for(User u : users){
			u.setFriends(null);
			u.setReceivedFriendRequest(null);
			u.setSentFriendRequest(null);
			u.setChat(null);
			u.setComments(null);
			u.setLikes(null);
			u.setPosts(null);
			u.setShared(null);
		}
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}

}