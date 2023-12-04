package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.Media;
import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.PostException;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.service.MediaService;
import com.groupnine.mediasocial.service.PostService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/post")
public class PostController {
	@Autowired 
	PostService postService;
	
	@Autowired
	MediaService mediaService;
	
//	@GetMapping("/recomment/{userId}")
//	public ResponseEntity<List<Post>> getRecommentPost(@PathVariable Long userId) {
//		User user = postService.findUserById(userId);
//		
//		return new ResponseEntity<List<Post>>(,HttpStatus.OK);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findPostById(@PathVariable Long id) throws PostException{
		return new ResponseEntity<>(postService.findPostById(id), HttpStatus.OK);
	}
	
	@PostMapping("/newpost")
	public Post savePost(@Valid @RequestBody Post post) { 
		List<Media> listMedia = post.getMedia();
		if (listMedia.size() > 0) {
			for (Media media : listMedia) {
				mediaService.saveMedia(media);
			}
		}
		
        return postService.savePost(post); 
    }
	
	@DeleteMapping("/{id}")
	public String deletePostById(@PathVariable("id") long postId) {
		try {
			Post post = postService.findPostById(postId);
			List<Media> listMedia = post.getMedia();
			if (listMedia.size() > 0) {
				for (Media media : listMedia) {
					mediaService.deleteMediaById(media.getMediaId());
				}
			}
			postService.deletePostById(postId);
		} catch (PostException e) {
			e.printStackTrace();
		}
		return "Deleted";
	}
	
	@PutMapping("/{id}") 
    public Post updatePost(@RequestBody Post post, @PathVariable("id") long postId) { 
        return postService.updatePost(post, postId); 
    } 
}
