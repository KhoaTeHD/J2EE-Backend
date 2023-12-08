package com.groupnine.mediasocial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Chatmessage;
import com.groupnine.mediasocial.repository.ChatmessageRepository;

@Service
public class ChatService {
	
	@Autowired
    ChatmessageRepository chatRepository;
	
    public List<Chatmessage> saveMessages(List<Chatmessage> listMessage){
        for(Chatmessage message : listMessage){
            chatRepository.save(message);
        }
        return listMessage;
    }

    public List<Chatmessage> getMessageByRoomId(String roomId){
    	List<Chatmessage> chatmessages = chatRepository.getChatMessageByRoomId(roomId);
    	
    	for (Chatmessage chatmessage : chatmessages) {
			chatmessage.getUser().setReceivedFriendRequest(null);
			chatmessage.getUser().setSentFriendRequest(null);
			chatmessage.getUser().setChat(chatmessages);
			chatmessage.getUser().setComments(null);
			chatmessage.getUser().setFriends(null);
			chatmessage.getUser().setLikes(null);
			chatmessage.getUser().setPosts(null);
		}
    	
        return chatmessages;
    }
}
