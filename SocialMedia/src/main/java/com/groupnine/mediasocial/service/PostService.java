package com.groupnine.mediasocial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.exception.PostException;
import com.groupnine.mediasocial.repository.PostRepository;

@Service
public class PostService implements IPostService{
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post findPostById(long postId) throws PostException {
		Optional<Post> opt = postRepository.findById(postId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new PostException("Post " + postId + " doesn't exist");
	}

	@Override
	public Post savePost(Post post){
		return postRepository.save(post);
	}

	@Override
	public void deletePostById(long postID) {
		postRepository.deleteById(postID); 
	}

	@Override
	public Post updatePost(Post post, long postId) {
		return postRepository.save(post);	
	}
}
