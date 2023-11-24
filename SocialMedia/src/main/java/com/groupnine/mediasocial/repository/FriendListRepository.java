package com.groupnine.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.FriendList;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {
	
}
