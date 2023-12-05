package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.FriendRequest;
import com.groupnine.mediasocial.exception.UserException;

@Service
public interface FriendRequestService {
	
	public boolean saveRequest(Long sender, Long receiver) throws UserException;
	
	public boolean acceptRequest(Long sender, Long receiver) throws UserException;
	
	public boolean denyRequest(Long sender, Long receiver) throws UserException;
}
