package com.groupnine.mediasocial.payload.request;

public class CommentRequest {
	
	private long commentId;
	private String content;
	private long replyFor;
	private long userId;
	private long postId;
	
	public CommentRequest(long commentId, String content, long replyFor, long userId, long postId) {
		this.commentId = commentId;
		this.content = content;
		this.replyFor = replyFor;
		this.userId = userId;
		this.postId = postId;
	}
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getReplyFor() {
		return replyFor;
	}
	public void setReplyFor(long replyFor) {
		this.replyFor = replyFor;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
}
