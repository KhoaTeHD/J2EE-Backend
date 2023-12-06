package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Comment;
import com.groupnine.mediasocial.repository.CommentRepository;

@Service
public class CommentService implements ICommentService{
	
	@Autowired 
	CommentRepository commentRepository;

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteCommentById(long commentId) {
		commentRepository.deleteById(commentId);
	}

}
