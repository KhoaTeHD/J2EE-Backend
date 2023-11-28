package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

@Service
public interface FriendListService {
	
	public boolean Unfriend(Long userId, Long friendId);
}
