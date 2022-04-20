package com.jersey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.Random;

import com.jersey.bean.UserBean;
import com.jersey.dbconn.DbConnectionProvider;

public class LoginDao 
{
	public static String LoginDao(UserBean rs)
	{
		String status;
		
		Connection con = DbConnectionProvider.getConnection();
		
		try {
			
			PreparedStatement ps1 = con.prepareStatement("select * from users where email=? and pass=?");
			ps1.setString(1, rs.getEmail());
			ps1.setString(2, rs.getPass());
			ResultSet rrs = ps1.executeQuery();
			
			if(rrs.next())
			{
				status = rrs.getString("status");
				
				if(status.equals("active"))
				{
					PreparedStatement ps2 = con.prepareStatement("select * from users where email=? and pass=?");
					ps2.setString(1, rs.getEmail());
					ps2.setString(2, rs.getPass());
					ResultSet rrs2 = ps1.executeQuery();
					
					if(rrs2.next()) {
						return "Login Success!!!";
					}
					else {
						return "fail";
					}
					
				}
				
				return "Please verify the user!!!!";
			}
			else 
			{
					return "Login Failed!!!";
			}
				
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return "Failed to login!!!";
	}
}
