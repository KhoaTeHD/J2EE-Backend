package com.groupnine.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

}
