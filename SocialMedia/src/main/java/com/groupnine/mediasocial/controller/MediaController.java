package com.groupnine.mediasocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.Media;
import com.groupnine.mediasocial.service.MediaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/media")
public class MediaController {
	
	@Autowired
	MediaService mediaService;
	
	@PostMapping("/newmedia")
	public Media saveNewMedia(@Valid @RequestBody Media media) {
		return mediaService.saveMedia(media);
	}

}
