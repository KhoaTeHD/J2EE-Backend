package com.groupnine.mediasocial.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User> opt = userRepository.findById(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not exist with id: "+ userId);
	}
	
	@Override
	public List<User> findUserByIds(List<Long> userIds) throws UserException {
		
		List<User> users = userRepository.findAllUsersByUserIds(userIds);
		
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		List<User> users = userRepository.findByQuery(query);
		
		if(users.size()==0) {
			throw new UserException("user not found");
		}
		
		return users;
	}
	
	@Override
	public int getMutualFriends(Long CurrentUserId, Long targetUserId) throws UserException {
		
		Optional<User> currentUser = userRepository.findById(CurrentUserId);		
		Optional<User> targetUser = userRepository.findById(targetUserId);
		
		if(currentUser.isPresent() && targetUser.isPresent()) {
			List<User> currentFriends = currentUser.get().getFriends();
			List<User> targetFriends = targetUser.get().getFriends();
			
			for (int i = 0; i < targetFriends.size(); i++) {
				User user = targetFriends.get(i);
				if(!currentFriends.remove(user)) {
					targetFriends.remove(user);
					i--;
				}
			}
			
			return targetFriends.size();
		}
		
		throw new UserException("you can't get mutual friends");
	}

	@Override
	public List<User> findFriendForId(Long userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		List<User> frList = user.get().getFriends();
		List<User> uList = userRepository.findAll();
		uList.remove(user.get());
		
		if(!frList.isEmpty()) {
			for(User u : frList) {
				if(uList.contains(u)) {
					uList.remove(u);
				}
			}
		}
		
		Collections.sort(uList, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				if(o1.getFriends().size() < o2.getFriends().size()) {
					return 1;
				}
				else if(o1.getFriends().size() > o2.getFriends().size()) {
					return -1;
				}
				return 0;
			}
		});
		
		if(uList.size() > 5) {
			uList = uList.subList(0, 4);
		}

		return uList;
	}
}
