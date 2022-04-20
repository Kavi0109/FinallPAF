package com.jersey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jersey.bean.OtpBean;
import com.jersey.dbconn.DbConnectionProvider;

public class OtpDao 
{
	
	public String verifyotp(OtpBean o) 
	{
		Connection c = DbConnectionProvider.getConnection();
		try 
		{
			PreparedStatement ps1 = c.prepareStatement("select * from users where otp=?");
			ps1.setString(1, o.getOtp());
			ResultSet rs = ps1.executeQuery();
			if(rs.next())
			{
				PreparedStatement ps2 = c.prepareStatement("update users set status=? where otp=?");
				ps2.setString(1, "active");
				ps2.setString(2, o.getOtp());
				int i = ps2.executeUpdate();
				
				if(i>0)
				{
					return "User_Activated!!";
				}
				else
				{
					return "User_Not_Activated!!!";
				}
			}
			else
			{
				return "Invalid OTP";
			}
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "Invalid OTP";
	}

}
