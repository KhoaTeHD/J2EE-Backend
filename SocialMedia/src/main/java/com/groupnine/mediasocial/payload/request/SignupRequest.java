package com.groupnine.mediasocial.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {

	@NotBlank
	@Size(max = 50)
	@Email
	private String gmail;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	@NotBlank
	@Size(min = 6, max = 30)
	private String profileName;


	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


}