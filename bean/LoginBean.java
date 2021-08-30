package com.tasklist.bean;

public class LoginBean 
{
	private String userName;
	private String password;
	
	public LoginBean(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
