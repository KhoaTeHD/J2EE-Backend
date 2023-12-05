package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.exception.PostException;

@Service
public interface IPostService {
	
	public Post savePost(Post post)throws PostException;
	
	public Post findPostById(long postId) throws PostException;
	
	public void deletePostById(long postID); 
	
	public Post updatePost(Post post, long departmentId); 
}
