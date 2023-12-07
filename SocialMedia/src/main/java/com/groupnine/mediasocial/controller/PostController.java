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

import com.groupnine.mediasocial.entity.Comment;
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
	
	@GetMapping("/recomment/{userId}")
	public ResponseEntity<List<Post>> getRecommentPost(@PathVariable Long userId){
		List<Post> listPost = postService.getPostsOfFriends(userId);
		for (Post post : listPost) {
			
			post.getUser().setComments(null);
			post.getUser().setFriends(null);
			//post.getUser().setLikes(null);
			post.getUser().setReceivedFriendRequest(null);
			post.getUser().setSentFriendRequest(null);
			
			post.setShared(null);
			
			List<Comment> listComment = post.getComments();
			List<Media> listMedia = post.getMedia();
			
			for (Comment comment: listComment) {
				comment.getUser().setComments(null);
				comment.getUser().setFriends(null);
				comment.getUser().setLikes(null);
				comment.getUser().setReceivedFriendRequest(null);
				comment.getUser().setSentFriendRequest(null);
				
				comment.getPost().setComments(null);
				comment.getPost().setLikes(null);
				comment.getPost().setMedia(null);
				comment.getPost().setShared(null);
				//comment.getPost().setUser(null);
			}
			
			for (Media media : listMedia) {
				media.setPostid(null);
			}
			
		}
		return new ResponseEntity<List<Post>>(listPost, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findPostById(@PathVariable Long id) throws PostException{
		
		Post post = postService.findPostById(id);
		
		post.getUser().setComments(null);
		post.getUser().setFriends(null);
		post.getUser().setLikes(null);
		post.getUser().setReceivedFriendRequest(null);
		post.getUser().setSentFriendRequest(null);
		
		post.setShared(null);
		
		List<Comment> listComment = post.getComments();
		List<Media> listMedia = post.getMedia();
		
		for (Comment comment: listComment) {
			comment.getUser().setComments(null);
			comment.getUser().setFriends(null);
			comment.getUser().setLikes(null);
			comment.getUser().setReceivedFriendRequest(null);
			comment.getUser().setSentFriendRequest(null);
			
			//comment.getPost().setComments(null);
			//comment.getPost().setLikes(null);
			//comment.getPost().setMedia(null);
			//comment.getPost().setShared(null);
			//comment.getPost().setUser(null);
			comment.setPost(null);
		}
		
		for (Media media : listMedia) {
			media.setPostid(null);
		}
		
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@PostMapping("/newpost")
	public Post savePost(@Valid @RequestBody Post post) { 
//		List<Media> listMedia = post.getMedia();
//		if (listMedia != null) {
//			for (Media media : listMedia) {
//				mediaService.saveMedia(media);
//			}
//		}
		
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
