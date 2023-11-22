package com.groupnine.mediasocial.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;

@Service
public interface UserService {

	public User registerUser(User user) throws UserException;
	public User findUserById(Long userId) throws UserException;
	public User findUserProfile(String token) throws UserException;
	public User findUserByUsername(String username) throws UserException;
	
	public List<User> findUserByIds(List<Long> userIds) throws UserException;
	
	public List<User> searchUser(String query) throws UserException;
	
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
	
	public int getMutualFriends(Long CurrentUserId, Long targetUserId) throws UserException;
}
