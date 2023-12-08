package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.Chatmessage;
import com.groupnine.mediasocial.service.ChatService;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
    ChatService chatService;
	
    @MessageMapping("/send/chatroom/{roomId}")
    @SendTo("/topic/chatroom/{roomId}")
    public Chatmessage sendMessage(@Payload Chatmessage chatMessage, @DestinationVariable String roomId){
        return  chatMessage;
    }

    @PostMapping("/save")
    public List<Chatmessage> saveMessage(@RequestBody List<Chatmessage> listMessage){
        return chatService.saveMessages(listMessage);
    }

    @GetMapping("/{roomId}")
    public List<Chatmessage> getMessageByRoomId(@PathVariable String roomId){
        return chatService.getMessageByRoomId(roomId);
    }
}
