package com.jersey.bean;

public class OtpBean 
{
	private String otp;
	
	public OtpBean(String otp) 
	{
		this.setOtp(otp);
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
