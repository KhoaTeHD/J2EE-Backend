package com.groupnine.mediasocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.Post;
import com.groupnine.mediasocial.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findByUserInOrderByCreatedDateDesc(List<User> friends);
}
