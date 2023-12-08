package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Media;


@Service
public interface IMediaService {
	public Media saveMedia(Media media);
	
	public void deleteMediaById(long mediaID); 
	
	public void deleteMediaByPostId(long PostId);
}
