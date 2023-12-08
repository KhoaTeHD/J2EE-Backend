package com.groupnine.mediasocial.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
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
import com.groupnine.mediasocial.entity.Reaction;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.PostException;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.service.CommentService;
import com.groupnine.mediasocial.service.MediaService;
import com.groupnine.mediasocial.service.PostService;
import com.groupnine.mediasocial.service.ReactionService;
import com.zaxxer.hikari.metrics.dropwizard.CodaHaleMetricsTracker;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/post")
public class PostController {
	@Autowired 
	PostService postService;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired 
	ReactionService reactionService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/recomment/{userId}")
	public ResponseEntity<List<Post>> getRecommentPost(@PathVariable Long userId){
		List<Post> listPost = postService.getPostsOfFriends(userId);
		for (Post post : listPost) {
			
			try {
				post.getUser().setComments(null);
				post.getUser().setFriends(null);
				post.getUser().setLikes(null);
				post.getUser().setReceivedFriendRequest(null);
				post.getUser().setSentFriendRequest(null);
				post.getUser().setChat(null);
				
				post.setShared(null);
				post.getUser().setPosts(null);
				
				List<Comment> listComment = post.getComments();
				
				for (Comment comment: listComment) {
					comment.getUser().setComments(null);
					comment.getUser().setFriends(null);
					comment.getUser().setLikes(null);
					comment.getUser().setReceivedFriendRequest(null);
					comment.getUser().setSentFriendRequest(null);
					comment.getUser().setChat(null);
					
					comment.getPost().setComments(null);
					comment.getPost().setLikes(null);
					comment.getPost().setMedia(null);
					comment.getPost().setShared(null);
					//comment.getPost().setUser(null);
					comment.setReplyFor(null);
					for (Comment cmt : comment.getReplies()) {
						cmt.setReplyFor(null);
						cmt.setUser(null);
						cmt.setPost(null);
					}
				}
				
				
				List<Media> listMedia = post.getMedia();
				for (Media media : listMedia) {
					media.setPostid(null);
				}
				
				List<Reaction> listReaction = post.getLikes();
				for (Reaction reaction : listReaction) {
					reaction.setPost(null);
					reaction.getUser().setPosts(null);
				}
			}
			catch (Exception e) {
			}
			
		}
		return new ResponseEntity<List<Post>>(listPost, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findPostById(@PathVariable Long id) throws PostException{
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Post post = postService.findPostById(id);
		
		post.getUser().setComments(null);
		post.getUser().setFriends(null);
		post.getUser().setLikes(null);
		post.getUser().setReceivedFriendRequest(null);
		post.getUser().setSentFriendRequest(null);
		post.getUser().setPosts(null);
		post.getUser().setChat(null);
		
		post.setShared(null);
		
		
		List<Comment> listComment = post.getComments();
		List<Media> listMedia = post.getMedia();
		
		List<Comment> listCommentnew = new ArrayList<Comment>();
		
		for(Comment comment: listComment) {
			if(comment.getReplyFor() != null) {
				continue;
			}
			listCommentnew.add(comment);
		}
		
		for (Comment comment: listCommentnew) {
			comment.getUser().setComments(null);
			comment.getUser().setFriends(null);
			comment.getUser().setLikes(null);
			comment.getUser().setReceivedFriendRequest(null);
			comment.getUser().setSentFriendRequest(null);
			comment.getUser().setPosts(null);
			comment.getUser().setChat(null);
			//comment.setReplies(null);

			//comment.getPost().setComments(null);
			//comment.getPost().setLikes(null);
			//comment.getPost().setMedia(null);
			//comment.getPost().setShared(null);
			//comment.getPost().setUser(null);
			comment.setPost(null);
//			comment.setReplyFor(null);
			if(comment.getReplyFor() != null) {
				listComment.remove(comment);
				continue;
//				comment.getReplyFor().setPost(null);
//				comment.getReplyFor().getUser().setComments(null);
//				comment.getReplyFor().getUser().setFriends(null);
//				comment.getReplyFor().getUser().setLikes(null);
//				comment.getReplyFor().getUser().setReceivedFriendRequest(null);
//				comment.getReplyFor().getUser().setSentFriendRequest(null);
//				comment.getReplyFor().getUser().setPosts(null);
			}
			
			if(comment.getReplies() != null) {
				for (Comment cmt : comment.getReplies()) {
					cmt.setReplyFor(null);
					//cmt.setUser(null);
					cmt.getUser().setComments(null);
					cmt.getUser().setFriends(null);
					cmt.getUser().setLikes(null);
					cmt.getUser().setReceivedFriendRequest(null);
					cmt.getUser().setSentFriendRequest(null);
					cmt.getUser().setPosts(null);
					cmt.getUser().setChat(null);
					
					cmt.setPost(null);
					cmt.setReplies(null);
					cmt.setReplyFor(null);
				}
			}
			
			
//			try {
//				for (Comment cmt : comment.getReplies()) {
//					cmt.setReplyFor(null);
//					//cmt.setUser(null);
//					cmt.getUser().setComments(null);
//					cmt.getUser().setFriends(null);
//					cmt.getUser().setLikes(null);
//					cmt.getUser().setReceivedFriendRequest(null);
//					cmt.getUser().setSentFriendRequest(null);
//					cmt.getUser().setPosts(null);
//					
//					cmt.setPost(null);
//					cmt.setReplies(null);
					
//					cmt.getUser().setFriends(null);
//					cmt.getUser().setPosts(null);
//					cmt.getUser().setComments(null);
//					cmt.getUser().setSentFriendRequest(null);
//					cmt.getUser().setReceivedFriendRequest(null);
//					cmt.getUser().setLikes(null);
//					cmt.getUser().setShared(null);
//					
//					cmt.getPost().setMedia(null);
//					cmt.getPost().setComments(null);
//					cmt.getPost().setLikes(null);
//					cmt.getPost().setShared(null);
//					cmt.getPost().setUser(null);
					
//					cmt.getReplyFor().setReplies(null);
//					cmt.getReplyFor().setReplyFor(null);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
		}
		post.setComments(listCommentnew);
		
		for (Media media : listMedia) {
			media.setPostid(null);
		}
		
		List<Reaction> listReaction = post.getLikes();
		for (Reaction reaction : listReaction) {
			reaction.setPost(null);
			reaction.getUser().setPosts(null);
			reaction.getUser().setFriends(null);
			reaction.getUser().setComments(null);
			reaction.getUser().setLikes(null);
			reaction.getUser().setReceivedFriendRequest(null);
			reaction.getUser().setSentFriendRequest(null);
			reaction.getUser().setChat(null);
		}
		
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@PostMapping("/newpost")
	public Post savePost(@Valid @RequestBody Post post) { 
		
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
			
			List<Reaction> listReaction = post.getLikes();
			if (listReaction.size() > 0) {
				for (Reaction reaction : listReaction) {
					reactionService.deleteReaction(reaction.getId());
				}
			}
			
			List<Comment> listComment = post.getComments();
			if (listComment.size() > 0) {
				for (Comment comment : listComment) {
					commentService.deleteCommentById(comment.getCommentId());
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
