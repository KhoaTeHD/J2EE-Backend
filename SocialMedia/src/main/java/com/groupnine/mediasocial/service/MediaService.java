package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Media;
import com.groupnine.mediasocial.repository.MediaRepository;

@Service
public class MediaService implements IMediaService{
	
	@Autowired
	MediaRepository mediaRepository;

	@Override
	public Media saveMedia(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public void deleteMediaById(long mediaID) {
		mediaRepository.deleteById(mediaID);
	}

}
