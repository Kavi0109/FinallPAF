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
		//int otp = new Random().nextInt(345);
		Connection con = DbConnectionProvider.getConnection();
		
		try {
			
			PreparedStatement ps1 = con.prepareStatement("select * from user where name=? and email=?");
			ps1.setString(1, rs.getName());
			ps1.setString(2, rs.getEmail());
			ResultSet rrs = ps1.executeQuery();
			
			if(rrs.next())
			{
				return "Login Success!!!";
			}
		else {
				/*PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?,?)");
				ps.setString(1, rs.getName());
				ps.setString(2, rs.getEmail());
				ps.setString(3, rs.getPass());
				ps.setString(4, rs.getMobile());
				ps.setInt(5, otp);
				ps.setString(6, "inactive");
				
				int i = ps.executeUpdate();
				if(i>0)
				{
					return "Successfully Registered!!!";
				}
				else
				{*/
					return "Login Failed!!!";
				}
				
			//}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return "Failed to register";
	}
}
