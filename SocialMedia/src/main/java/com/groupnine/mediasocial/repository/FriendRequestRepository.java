package com.groupnine.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.FriendRequest;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
	
	@Query(value = "SELECT * from friendrequest fr where fr.user_id = :senderId or fr.friend_id = :receiverId ", nativeQuery = true)
	public FriendRequest findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
