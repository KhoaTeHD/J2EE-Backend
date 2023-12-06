package com.groupnine.mediasocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.entity.User;
import com.groupnine.mediasocial.exception.PostException;
import com.groupnine.mediasocial.repository.FriendListRepository;
import com.groupnine.mediasocial.repository.PostRepository;
import com.groupnine.mediasocial.repository.UserRepository;

@Service
public class PostService implements IPostService{
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
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

	@Override
	public List<Post> getPostsOfFriends(Long userId) {
		Optional<User> opt = userRepository.findById(userId);
		if(opt.isPresent()) {
			User user = opt.get();
			List<User> listUser= user.getFriends();
			listUser.add(user);
			return postRepository.findByUserInOrderByCreatedDateDesc(listUser);
		}
		return null;
	}
}
