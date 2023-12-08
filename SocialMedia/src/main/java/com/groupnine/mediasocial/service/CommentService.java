package com.groupnine.mediasocial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Comment;
import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.exception.PostException;
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

	@Override
	public Comment getCommentById(long commentId) {
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

}
