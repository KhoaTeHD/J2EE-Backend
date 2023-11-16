package com.groupnine.mediasocial.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 20)
    private String profileName;

    @Column(nullable = false, length = 30)
    private String gmail;

    @Column(length = 50)
    private String biography;

    @Column
    private Integer gender;

    @Column
    private LocalDate birthday;
    
    @Column
    private String avatar;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate signupDate;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "friendlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;
    
    @OneToMany(mappedBy = "sender")
    private List<FriendRequest> sentFriendRequest;
    
    @OneToMany(mappedBy = "receiver")
    private List<FriendRequest> receivedFriendRequest;
    
    @OneToMany(mappedBy = "user")
    private List<Share> shared;
    
    @OneToMany(mappedBy = "user")
    private List<Reaction> likes;
    
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(LocalDate signupDate) {
		this.signupDate = signupDate;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<FriendRequest> getSentFriendRequest() {
		return sentFriendRequest;
	}

	public void setSentFriendRequest(List<FriendRequest> sentFriendRequest) {
		this.sentFriendRequest = sentFriendRequest;
	}

	public List<FriendRequest> getReceivedFriendRequest() {
		return receivedFriendRequest;
	}

	public void setReceivedFriendRequest(List<FriendRequest> receivedFriendRequest) {
		this.receivedFriendRequest = receivedFriendRequest;
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
    
}