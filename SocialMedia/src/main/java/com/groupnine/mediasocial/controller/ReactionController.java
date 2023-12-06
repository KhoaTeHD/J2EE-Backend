package com.groupnine.mediasocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupnine.mediasocial.entity.Reaction;
import com.groupnine.mediasocial.service.ReactionService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reaction")
public class ReactionController {
	@Autowired
	ReactionService reactionService;
	
	
	@PutMapping("/new")
	public Reaction saveReaction(@Valid @RequestBody Reaction reaction) {
		return reactionService.saveReaction(reaction);
	}
	
	@DeleteMapping("/{id}")
	public String deleteReactionById(@PathVariable("id") long reactionId) {
		reactionService.deleteReaction(reactionId);
		return "Deleted";
	}
}
