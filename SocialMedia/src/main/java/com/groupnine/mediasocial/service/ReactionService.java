package com.groupnine.mediasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupnine.mediasocial.entity.Reaction;
import com.groupnine.mediasocial.repository.ReactionRepository;

@Service
public class ReactionService implements IReactionService{
	
	@Autowired
	ReactionRepository reactionRepository;

	@Override
	public Reaction saveReaction(Reaction reaction) {
		return reactionRepository.save(reaction);
	}

	@Override
	public void deleteReaction(long reactionID) {
		reactionRepository.deleteById(reactionID);
	}

}
