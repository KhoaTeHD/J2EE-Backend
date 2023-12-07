package com.groupnine.mediasocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{
	
	@Query(value = "SELECT * from reaction r where r.user_id = :userId and r.post_id = :postId", nativeQuery = true)
	Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId);
}
