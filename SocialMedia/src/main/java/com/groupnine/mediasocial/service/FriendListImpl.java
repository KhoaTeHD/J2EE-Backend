package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.FriendList;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.repository.FriendListRepository;


@Service
public class FriendListImpl implements FriendListService {
	
	@Autowired
	private FriendListRepository friendRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public boolean Unfriend(Long userId, Long friendId) {
		FriendList friend1 = friendRepository.findByUserIdAndFriendId(userId, friendId);
		FriendList friend2 = friendRepository.findByUserIdAndFriendId(friendId, userId);
		
		try {
			friendRepository.delete(friend1);
			friendRepository.delete(friend2);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean addfriend(Long userId, Long friendId) throws UserException {
		User user = userService.findUserById(userId);
		User friend = userService.findUserById(friendId);
		FriendList fl1 = new FriendList();
		FriendList fl2 = new FriendList();
		
		fl1.setUser(user);
		fl1.setFriend(friend);
		
		fl2.setUser(friend);
		fl2.setFriend(user);
		
		try {
			friendRepository.save(fl1);
			friendRepository.save(fl2);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
}
