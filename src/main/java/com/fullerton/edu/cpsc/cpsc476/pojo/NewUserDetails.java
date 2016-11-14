package com.fullerton.edu.cpsc.cpsc476.pojo;

public class NewUserDetails {
	private String username;
	private String emailID;
	private String password;
	private Boolean isGuestUser;

	public NewUserDetails(String username, String emailID, String password, Boolean isGuestUser) {
		this.username = username;
		this.password = password;
		this.emailID = emailID;
		this.isGuestUser = isGuestUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
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
