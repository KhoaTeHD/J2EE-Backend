package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.FriendRequest;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.repository.FriendRequestRepository;

@Service
public class FriendRequestImpl implements FriendRequestService {
	
	@Autowired
	private FriendRequestRepository frRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendListService flService;

	@Override
	public boolean saveRequest(Long sender, Long receiver) throws UserException {
		User send = userService.findUserById(sender);
		User receive = userService.findUserById(receiver);
		FriendRequest frIsExisting = frRepository.findBySenderIdOrReceiverId(receiver, sender);
		
		if(frIsExisting != null) {
			if(flService.addfriend(sender, receiver)){
				frRepository.delete(frIsExisting);
				return true;
			}
		}
		
		FriendRequest fr = new FriendRequest();
		fr.setSender(send);
		fr.setReceiver(receive);
		
		try {
			frRepository.save(fr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean acceptRequest(Long sender, Long receiver) throws UserException {
		FriendRequest fr = frRepository.findBySenderIdOrReceiverId(sender, receiver);
		
		try {
			flService.addfriend(sender, receiver);
			frRepository.delete(fr);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean denyRequest(Long sender, Long receiver) throws UserException {
		FriendRequest fr = frRepository.findBySenderIdOrReceiverId(sender, receiver);
		try {
			frRepository.delete(fr);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
