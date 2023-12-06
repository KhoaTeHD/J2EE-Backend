package com.groupnine.mediasocial.service;

import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Reaction;

@Service
public interface IReactionService {
	
	public Reaction saveReaction(Reaction reaction);
	
	public void deleteReaction(long reactionID);
	
}
