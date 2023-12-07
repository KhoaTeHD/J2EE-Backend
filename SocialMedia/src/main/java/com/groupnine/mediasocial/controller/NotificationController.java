package com.groupnine.mediasocial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pusher.rest.Pusher;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notify")
public class NotificationController {
	
	private Pusher pusher = new Pusher("1721160", "7c9f018d64bec3a78677", "f06623e679942934f917");;

    public NotificationController() {
    	pusher.setCluster("ap3");
    	pusher.setEncrypted(true);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody String notificationData) {
        // Xử lý logic và dữ liệu từ request

        // Gửi thông báo tới Pusher
    	if(notificationData.contains("=")) {
    		String[] s = notificationData.split("=");
    		pusher.trigger("my-channel", "my-event", s[0]);
    	}
    	else {
    		pusher.trigger("my-channel", "my-event", notificationData);
    	}

        return ResponseEntity.ok("Notification sent");
    }
}
