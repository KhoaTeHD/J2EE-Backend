package com.groupnine.mediasocial.payload.request;

public class ReactionRequest {
	
	private long id;
	private long postId;
	private long userId;
	
	public ReactionRequest(long id, long postId, long userId) {
		this.id = id;
		this.postId = postId;
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
