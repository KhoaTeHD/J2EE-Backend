package com.groupnine.mediasocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupnine.mediasocial.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM media WHERE post_id = :postId", nativeQuery = true)
	Optional<?> deleteByPostId(Long postId);

}
