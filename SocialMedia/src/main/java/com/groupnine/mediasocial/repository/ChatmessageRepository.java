package com.groupnine.mediasocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groupnine.mediasocial.entity.Chatmessage;

@Repository
public interface ChatmessageRepository extends JpaRepository<Chatmessage,Long> {
	    @Query(value = "select cm from Chatmessage cm where cm.room_id =:roomId")
	    List<Chatmessage> getChatMessageByRoomId(String roomId);
}
