package com.jersey.bean;

public class UserBean 
{
	private String name,email,pass,mobile;

	/*public UserBean(String asString, String asString2, String asString3, String asString4)
	{
		this.setName(asString);
		this.setEmail(asString2);
		this.setMobile(asString3);
		this.setPass(asString4);
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
