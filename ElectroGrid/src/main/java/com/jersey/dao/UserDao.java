package com.jersey.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.jersey.bean.UserBean;
import com.jersey.dbconn.DbConnectionProvider;
import com.jersey.utils.SendMail;

public class UserDao 
{
	public static String registerDao(UserBean rs)
	{
		int otp = new Random().nextInt(345);
		int accountNo = new Random().nextInt(1000);
		Connection con = DbConnectionProvider.getConnection();
		
		try {
			
			PreparedStatement ps1 = con.prepareStatement("select * from users where email=?");
			ps1.setString(1, rs.getEmail());
			ResultSet rrs = ps1.executeQuery();
			
			if(rrs.next())
			{
				return "Already Exists!!!";
			}
		else {
				PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
				ps.setString(1, rs.getName());
				ps.setString(2, rs.getEmail());
				ps.setString(3, rs.getPass());
				ps.setString(4, rs.getMobile());
				ps.setInt(5, accountNo);
				ps.setInt(6, otp);
				ps.setString(7, "inactive");
				
				int i = ps.executeUpdate();
				if(i>0)
				{
					boolean isSend = SendMail.sendMail(rs.getEmail(), "generated: ", "OTP "+otp);
					if(isSend==true) {
					return "Successfully Registered!!!";
					}else {
						return "Error while sending the email!!";
					}
				}
				else
				{
					return "Error when registration process!!!";
				}
				
				}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return "Failed to register";
	}
	
	public String readUsers()
	 {
	 String output = "";
	 try
	 {
		 Connection con = DbConnectionProvider.getConnection();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
		 }
		 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
	 "<th>Password</th>" +
	 "<th>Mobile</th>" +
	 "<th>AccountNo</th>" +
	 "<th>OTP</th>" +
	 "<th>Status</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from users";
	 
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String name = rs.getString("name");
	 String email = rs.getString("email");
	 String password = rs.getString("pass");
	 String mobile = rs.getString("mobile");
	 String accountNo = Integer.toString(rs.getInt("accountNo"));
	 String otp = Integer.toString(rs.getInt("otp"));
	 String status = rs.getString("status");
	 
	 // Add into the html table
	 output += "<tr><td>" + name + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + password + "</td>";
	 output += "<td>" + mobile + "</td>";
	 output += "<td>" + accountNo + "</td>";
	 output += "<td>" + otp + "</td>";
	 output += "<td>" + status + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='"+ "'>" + "</form></td></tr>";
	 }
	 //con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateTheUser(String name, String email, String password, String mobile)
	{
		 String output = "";
		 try
		 {
		 Connection con = DbConnectionProvider.getConnection();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; 
	     }
		 // create a prepared statement
		 String query = "UPDATE users SET name=?,pass=?,mobile=? WHERE email=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, password);
		 preparedStmt.setString(3, mobile);
		 preparedStmt.setString(4, email);
		 // execute the statement
		 preparedStmt.execute();
		 //con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	
	public String deleteUser(String email)
	 {
	 String output = "";
	 try
		 {
		 Connection con = DbConnectionProvider.getConnection();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; 
		 }
		 
		 // create a prepared statement
		 String query = "delete from users where email=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1,email);
		 
		 // execute the statement
		 preparedStmt.execute();
		 //con.close();
		 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
