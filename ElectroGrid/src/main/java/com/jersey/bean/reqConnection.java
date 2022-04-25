package com.jersey.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jersey.dbconn.DbConnectionProvider;

public class reqConnection {
	
			//A common method to connect to the 
//			private Connection connect()
//			{
//				 Connection con = null;
//				 try
//				 {
//					 Class.forName("com.mysql.jdbc.Driver");
//			
//					 //Provide the correct details: DBServer/DBName, username, password
//					 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/paf", "root", "");
//				 }
//				 catch (Exception e)
//				 {
//					 e.printStackTrace();
//				 }
//					 return con;
//			}
		
			
			public String insertConnection(String customerName, String connectionType, String requestLoad,String contractDemand, String address, String email)
			{
				String output = "";
				try
				{
					Connection con = DbConnectionProvider.getConnection();
					 if (con == null)
						 return "Error while connecting to the database for inserting."; 
					 // create a prepared statement
					 String query = " insert into connections(`connectionID`,`customerName`,`connectionType`,`requestLoad`,`contractDemand`,`address`,`email`)" + " values (?, ?, ?, ?, ?, ?, ?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, customerName);
					 preparedStmt.setString(3, connectionType);
					 preparedStmt.setString(4, requestLoad);
					 preparedStmt.setString(5, contractDemand);
					 preparedStmt.setString(6, address);
					 preparedStmt.setString(7, email);
					 
					 // execute the statement
					 
					 preparedStmt.execute();
					
					 output = "Inserted successfully";
				}
				catch (Exception e)
				{
					 output = "Error while inserting the item.";
					 System.err.println(e.getMessage());
				}
				return output;
			}
		
			public String readConnections()
			{
				String output = "";
				try
				{
					Connection con = DbConnectionProvider.getConnection();
					if (con == null)
						 return "Error while connecting to the database for reading.";
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Connection ID</th><th>Customer Name</th>" + "<th>Connection Type</th>" + "<th>Request Load</th>" + "<th>Contract Demand</th>" + "<th>Address</th>" + "<th>Email</th>" + "<th>Update</th><th>Remove</th></tr>";
				
					 String query = "select * from connections";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
						 String connectionID = Integer.toString(rs.getInt("connectionID"));
						 String customerName = rs.getString("customerName");
						 String connectionType = rs.getString("connectionType");
						 String requestLoad = rs.getString("requestLoad");
						 String contractDemand = rs.getString("contractDemand");
						 String address = rs.getString("address");
						 String email = rs.getString("email");
						 // Add into the html table
						 output += "<tr><td>" + connectionID + "</td>";
						 output += "<td>" + customerName + "</td>";
						 output += "<td>" + connectionType + "</td>";
						 output += "<td>" + requestLoad + "</td>";
						 output += "<td>" + contractDemand + "</td>";
						 output += "<td>" + address + "</td>";
						 output += "<td>" + email + "</td>";
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='itemID' type='hidden' value='" + connectionID + "'>" + "</form></td></tr>";
					 }
					 
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
		
		
			 public String readConnections(String Id)
			 {
				
				int convertedID = Integer.parseInt(Id);
				 String output = "";
				 try
				 {
					 Connection con = DbConnectionProvider.getConnection();
					 if (con == null)
						 return "Error while connecting to the database for reading.";
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Connection ID</th><th>Customer Name</th>" + "<th>Connection Type</th>" + "<th>Request Load</th>" + "<th>Contract Demand</th>" + "<th>Address</th>" + "<th>Email</th>" + "<th>Update</th><th>Remove</th></tr>";
						
					 String query = "select * from connections where connectionID='"+convertedID+"'";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
						 String connectionID = Integer.toString(rs.getInt("connectionID"));
						 String customerName = rs.getString("customerName");
						 String connectionType = rs.getString("connectionType");
						 String requestLoad = rs.getString("requestLoad");
						 String contractDemand = rs.getString("contractDemand");
						 String address = rs.getString("address");
						 String email = rs.getString("email");
						 // Add into the html table
						 output += "<tr><td>" + connectionID + "</td>";
						 output += "<td>" + customerName + "</td>";
						 output += "<td>" + connectionType + "</td>";
						 output += "<td>" + requestLoad + "</td>";
						 output += "<td>" + contractDemand + "</td>";
						 output += "<td>" + address + "</td>";
						 output += "<td>" + email + "</td>";
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='itemID' type='hidden' value='" + connectionID + "'>" + "</form></td></tr>";
					 }
					
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
			 
			 
			 
			 
			 
			 
			 
			 
			
			public String updateConnection(String ID, String customerName, String connectionType, String requestLoad,String contractDemand,String address, String email)
			{
				 String output = "";
				 try
				 {
					 Connection con = DbConnectionProvider.getConnection();
					 if (con == null)
						 return "Error while connecting to the database for updating."; 
					 // create a prepared statement
					 String query = "UPDATE connections SET customerName=?,connectionType=?,requestLoad=?,contractDemand=?,address=?,email=? WHERE connectionID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setString(1, customerName);
					 preparedStmt.setString(2, connectionType);
					 preparedStmt.setString(3, requestLoad);
					 preparedStmt.setString(4, contractDemand);
					 preparedStmt.setString(5, address);
					 preparedStmt.setString(6, email);
					 preparedStmt.setInt(7, Integer.parseInt(ID));
					 // execute the statement
					 preparedStmt.execute();
					
					 output = "Updated successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Error while updating the item.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			}
			
			public String deleteConnection(String connectionID)
			{
				 String output = "";
				 try
				 {
					 Connection con = DbConnectionProvider.getConnection();
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 // create a prepared statement
					 String query = "delete from connections where connectionID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(connectionID));
					 // execute the statement
					 preparedStmt.execute();
					
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
