package com.fullerton.edu.cpsc.cpsc476.pojo;

public class NewUserDetails {
	private String username;
	private String password;
	private Boolean isGuestUser;

	public NewUserDetails(String username, String password, Boolean isGuestUser) {
		this.username = username;
		this.password = password;
		this.isGuestUser = isGuestUser;
	}

	public NewUserDetails() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsGuestUser() {
		return isGuestUser;
	}

	public void setIsGuestUser(Boolean isGuestUser) {
		this.isGuestUser = isGuestUser;
	}
}
