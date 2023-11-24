//package com.groupnine.mediasocial.service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import com.groupnine.mediasocial.entity.User;
//import com.groupnine.mediasocial.exception.UserException;
//import com.groupnine.mediasocial.repository.UserRepository;
//
//@Service
//public class UserServiceImplementation implements UserService {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Override
//	public User registerUser(User user) throws UserException {
//		
//		Optional<User> isGmailExist = userRepository.findByGmail(user.getGmail());
//		
//		if(isGmailExist.isPresent()) {
//			throw new UserException("Username Is Already Taken...");
//		}
//		
//		if(user.getGmail() == null || user.getPassword() == null || user.getUsername() == null || user.getProfileName() == null) {
//			throw new UserException("All fields are required");
//		}
//		
//		User newUser = new User();
//		newUser.setGmail(user.getGmail());
//		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		newUser.setProfileName(user.getProfileName());
//		newUser.setSignupDate(LocalDate.now());
//		
//		
//		return userRepository.save(newUser);
//	}
//
//	@Override
//	public User findUserById(Long userId) throws UserException {
//		
//		Optional<User> opt = userRepository.findById(userId);
//		
//		if(opt.isPresent()) {
//			return opt.get();
//		}
//		throw new UserException("user not exist with id: "+ userId);
//	}
//
//	@Override
//	public User findUserProfile(String token) throws UserException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findUserByUsername(String username) throws UserException {
//		Optional<User> opt = userRepository.findByUsername(username);
//		
//		if(opt.isPresent()) {
//			return opt.get();
//		}
//		
//		throw new UserException("user not exist with username: "+ username);
//	}
//
//	@Override
//	public List<User> findUserByIds(List<Long> userIds) throws UserException {
//		
//		List<User> users = userRepository.findAllUsersByUserIds(userIds);
//		
//		return users;
//	}
//
//	@Override
//	public List<User> searchUser(String query) throws UserException {
//		List<User> users = userRepository.findByQuery(query);
//		
//		if(users.size()==0) {
//			throw new UserException("user not found");
//		}
//		
//		return users;
//	}
//
//	@Override
//	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
//		
//		if(updatedUser.getGmail()!=null) {
//			existingUser.setGmail(updatedUser.getGmail());
//		}
//		if(updatedUser.getBiography()!=null) {
//			existingUser.setBiography(updatedUser.getBiography());
//		}
//		if(updatedUser.getProfileName()!=null) {
//			existingUser.setProfileName(updatedUser.getProfileName());
//		}
//		if(updatedUser.getGender()!=null) {
//			existingUser.setGender(updatedUser.getGender());
//		}
//		if(updatedUser.getBirthday()!=null) {
//			existingUser.setBirthday(updatedUser.getBirthday());
//		}
//		if(updatedUser.getAvatar()!=null) {
//			existingUser.setAvatar(updatedUser.getAvatar());
//		}
//		if(updatedUser.getUserId()==existingUser.getUserId()) {
//			return userRepository.save(existingUser);
//		}
//		
//		throw new UserException("you can't update this usert");
//	}
//
//}
