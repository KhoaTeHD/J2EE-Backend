package com.groupnine.mediasocial.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.payload.request.UserChangePasswordRequest;
import com.groupnine.mediasocial.payload.request.UserChangeProfileRequest;
import com.groupnine.mediasocial.service.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.criteria.Path;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/userProfile/")
public class UserProfileController {
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		user.setFriends(null);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("numFriends/{id}")
	public int getNumFriends(@PathVariable Long id) throws UserException{
		User user = userService.findUserById(id);
		
		return user.getFriends().size();
	}
	
	@PostMapping("/updateProfile/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserChangeProfileRequest updateRequest, @PathVariable Long id) throws UserException{
		User currentUser = userService.findUserById(id);
		User updateUser = new User();
		updateUser.setUserId(updateRequest.getUserId());
		updateUser.setProfileName(updateRequest.getProfileName());
		updateUser.setBirthday(updateRequest.getBirthday());
		updateUser.setBiography(updateRequest.getBiography());
		updateUser.setGender(updateRequest.getGender());
		updateUser.setAvatar(updateRequest.getAvatar());
		User resultUser = userService.updateProfile(updateUser, currentUser);
		if(resultUser != null) {
			return new ResponseEntity<> ("Cập nhật thông tin người dùng thành công" ,HttpStatus.OK);
		}
		return new ResponseEntity<> ("Update thất bại", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/updatePassword/{id}")
	public ResponseEntity<?> updatePassword(@RequestBody UserChangePasswordRequest updateRequest, @PathVariable Long id) throws UserException{
		User currentUser = userService.findUserById(id);
		
		if(!encoder.matches(updateRequest.getOld_password(), currentUser.getPassword())) {
			return new ResponseEntity<> ("101", HttpStatus.OK);
		} else if(updateRequest.getOld_password().endsWith(updateRequest.getNew_password())) {
			return new ResponseEntity<> ("102", HttpStatus.OK);
		} else {
			currentUser.setPassword(encoder.encode(updateRequest.getNew_password()));
			int result = userService.updatePassword(currentUser);
			return result == 1 ? new ResponseEntity<> ("103", HttpStatus.OK) : new ResponseEntity<> ("104", HttpStatus.OK);
		}
	}
	
	private static final String UPLOAD_DIRECTORY = "public/images/";
	
    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestPart("avatar") MultipartFile avatar) throws IllegalStateException, java.io.IOException {
    	 try {
    	        if (!avatar.isEmpty()) {
    	            String fileName = avatar.getOriginalFilename();
    	            File newFile = new File(UPLOAD_DIRECTORY + fileName);
    	            
    	            // Nếu dung lượng ảnh quá 10MB
    	            if (avatar.getSize() > 10485760) { 
    	                return ResponseEntity.badRequest().body("Dung lượng ảnh quá lớn không thể upload vui lòng chọn < 10MB.");
    	            }
    	            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile))) {
    	                outputStream.write(avatar.getBytes());
    	                outputStream.flush();
    	            }

    	            String imageUrl = "http://localhost:8080/public/images/" + fileName; // Assuming your application is running on port 8080

    	            return ResponseEntity.ok().body(imageUrl);
    	        } else {
    	            throw new Exception();
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return ResponseEntity.badRequest().build();
    	    }
    	 
    }

    @GetMapping("/public/images/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable("imageName") String imageName) throws IOException, java.io.IOException {
        File imageFile = new File(UPLOAD_DIRECTORY + imageName);

        if (imageFile.exists()) {
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/public/images/{imageName}")
//    public ResponseEntity<?> getImage(@PathVariable("avatar") String avatar) throws IOException, java.io.IOException {
//        File imageFile = new File(UPLOAD_DIRECTORY + avatar);
//
//        if (imageFile.exists()) {
//            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
//    private static final String IMAGE_DIRECTORY = "public/images/";
//
//    @PostMapping("/uploadImage")
//    public String uploadImage(@RequestParam("filePath") String filePath) throws java.io.IOException {
//        try {
//            File sourceFile = new File(filePath);
//            File destFile = new File(IMAGE_DIRECTORY + sourceFile.getName());
//
//            FileInputStream fileInputStream = new FileInputStream(sourceFile);
//            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
//
//            byte[] buffer = new byte[1024];
//            int length;
//
//            while ((length = fileInputStream.read(buffer)) > 0) {
//                fileOutputStream.write(buffer, 0, length);
//            }
//
//            fileInputStream.close();
//            fileOutputStream.close();
//
//            return "File uploaded successfully!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "File upload failed!";
//        }
//    }
	
//	@GetMapping("numPost/{id}")
//	public int getNumPost(@PathVariable Long id) throws UserException{
//		User user = userService.findUserById(id);
//		
//		
//	}

}
