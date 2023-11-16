//package com.groupnine.mediasocial.controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.groupnine.mediasocial.entity.User;
//import com.groupnine.mediasocial.repository.UserRepository;
//
//@RestController
//public class UserRegisterController {
//	private final UserRepository repository;
//
//	UserRegisterController(UserRepository repository) {
//		this.repository = repository;
//	}
//
//	@GetMapping("/users")
//	List<User> all() {
//	}
//
//	@GetMapping("/check-login")
//	Boolean checkLogin(@RequestParam String email, @RequestParam String password) {
//		List<User> userLst = repository.checkLoginUser(email, password);
//		if (userLst.size() > 0) {
//			return true;
//		}
//		return false;
//	}
//
//}
