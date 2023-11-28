package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.FriendList;
import com.groupnine.mediasocial.repository.FriendListRepository;


@Service
public class FriendListImpl implements FriendListService {
	
	@Autowired
	private FriendListRepository friendRepository;

	@Override
	public boolean Unfriend(Long userId, Long friendId) {
		FriendList friend = friendRepository.findByUserIdAndFriendId(userId, friendId);
		
		try {
			friendRepository.delete(friend);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
}
