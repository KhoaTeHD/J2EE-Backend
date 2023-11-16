package com.groupnine.mediasocial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "media")
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long MediaId;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "media_type")
	private String type;
	
	@ManyToOne
    @JoinColumn(name = "post_id")
    private Post postid;

	public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Post getPostid() {
		return postid;
	}

	public void setPostid(Post postid) {
		this.postid = postid;
	}
	
	
}
