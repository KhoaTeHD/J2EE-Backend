package com.groupnine.mediasocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupnine.mediasocial.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{
	
	@Query(value = "SELECT * from reaction r where r.user_id = :userId and r.post_id = :postId", nativeQuery = true)
	Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM reaction WHERE user_id = :userId and post_id = :postId", nativeQuery = true)
	Optional<?> deleteReactionByPostIdAndUserId(Long userId, Long postId);
}
