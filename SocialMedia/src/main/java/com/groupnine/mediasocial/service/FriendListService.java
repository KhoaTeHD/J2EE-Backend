package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.exception.UserException;

@Service
public interface FriendListService {
	
	public boolean Unfriend(Long userId, Long friendId);
	
	public boolean addfriend(Long userId, Long friendId)throws UserException;
}
