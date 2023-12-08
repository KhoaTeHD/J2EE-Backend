package com.groupnine.mediasocial.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Comment;

@Service
public interface ICommentService {
	
	public Comment getCommentById(long commentId);
	
	public Comment saveComment(Comment comment);
	
	public void deleteCommentById(long commentId); 

}
