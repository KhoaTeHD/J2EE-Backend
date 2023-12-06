package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Comment;

@Service
public interface ICommentService {
	
	public Comment saveComment(Comment comment);
	
	public void deleteCommentById(long commentId); 

}
