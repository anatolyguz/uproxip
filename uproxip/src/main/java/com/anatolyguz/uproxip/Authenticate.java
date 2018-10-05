package com.anatolyguz.uproxip;

public class Authenticate {
	String UserName;
	String PasswordHash;
	String url;	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPasswordHash() {
		return PasswordHash;
	}
	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}

	public Authenticate(String userName, String passwordHash) {
		super();
		UserName = userName;
		PasswordHash = passwordHash;
		url="Authenticate";	
	}
	
	

	
	 }
	


