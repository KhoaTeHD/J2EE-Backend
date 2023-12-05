package com.groupnine.mediasocial.payload.request;

import java.time.LocalDate;

public class UserChangeProfileRequest {

    private long userId;
    
    private String profileName;
    
    private LocalDate birthday;

    private String biography;

    private Integer gender;
    
    private String avatar;

    public UserChangeProfileRequest(long userId, String profileName, LocalDate birthday, String biography,
			Integer gender, String avatar) {
		super();
		this.userId = userId;
		this.profileName = profileName;
		this.birthday = birthday;
		this.biography = biography;
		this.gender = gender;
		this.avatar = avatar;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
