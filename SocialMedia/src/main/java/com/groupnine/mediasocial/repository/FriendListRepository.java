package com.groupnine.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.FriendList;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {
	
	@Query(value = "SELECT * from friendlist f where f.user_id = :userid and f.friend_id = :friendid", nativeQuery = true)
	public FriendList findByUserIdAndFriendId(@Param("userid")Long userId,@Param("friendid") Long friendId);
}
