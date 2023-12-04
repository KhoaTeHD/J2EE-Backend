package com.groupnine.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{

}
