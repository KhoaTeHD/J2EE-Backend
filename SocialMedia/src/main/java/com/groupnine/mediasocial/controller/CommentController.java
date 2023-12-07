package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.Comment;
import com.groupnine.mediasocial.exception.PostException;
import com.groupnine.mediasocial.exception.UserException;
import com.groupnine.mediasocial.payload.request.CommentRequest;
import com.groupnine.mediasocial.service.CommentService;
import com.groupnine.mediasocial.service.PostService;
import com.groupnine.mediasocial.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired 
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PutMapping("/savecomment")
	public Comment savePost(@Valid @RequestBody Comment comment) { 
        return commentService.saveComment(comment); 
    }
	
	@PostMapping("/savecmt")
	public Comment saveComment(@Valid @RequestBody CommentRequest comment) throws PostException, UserException {
		Comment cmt = new Comment();
		cmt.setCommentId(comment.getCommentId());
		cmt.setContent(comment.getContent());
		cmt.setReplyFor(cmt.getReplyFor());
		cmt.setPost(postService.findPostById(comment.getPostId()));
		cmt.setUser(userService.findUserById(comment.getUserId()));
		return commentService.saveComment(cmt);
    }
}
