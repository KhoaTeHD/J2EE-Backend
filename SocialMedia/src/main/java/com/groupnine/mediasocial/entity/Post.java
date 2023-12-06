package com.groupnine.mediasocial.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;
	
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
    private Date createdDate;

    @Column(length = 300)
    private String caption;
    
    @OneToMany(mappedBy = "postid")
    private List<Media> media;
    
    @OneToMany(mappedBy = "post")
    private List<Share> shared;
    
    @OneToMany(mappedBy = "post")
    private List<Reaction> likes;
    
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    
    @ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public List<Share> getShared() {
		return shared;
	}

	public void setShared(List<Share> shared) {
		this.shared = shared;
	}

	public List<Reaction> getLikes() {
		return likes;
	}

	public void setLikes(List<Reaction> likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
