package com.jersey.bean;

import java.sql.*;

import com.jersey.dbconn.DbConnectionProvider;

public class Payment {

	// insert payment details
	
	public String insertPayment(String account, String amount, String date) {
		
		String output = "";
		
		try {
			
			// database connection
			Connection con = DbConnectionProvider.getConnection();
		
			// error while connecting
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
		
			// create a prepared statement
			String query = " insert into payment(`paymentId`,`accountNum`,`amount`,`date`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, account);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, date);
		
			// execute the statement
			preparedStmt.execute();
			
			// insert success message
			output = "Inserted Successfully!";
		
		}
		catch (Exception e) {
			
			// error while inserting
			output = "Error while inserting payment details!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

		
	// read payment details
	
	public String readPayment() {
		
		String output = "";
		
		try {
			
			// database connection
			Connection con = DbConnectionProvider.getConnection();
			
			// error while connecting
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment ID</th>" + "<th>Account Number</th>"
						+ "<th>Payment Amount</th>" + "<th>Payment Date</th>" + "<th>Delete</th>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				String paymentId = Integer.toString(rs.getInt("paymentId"));
				String accountNum = rs.getString("accountNum");
				String amount = Double.toString(rs.getDouble("amount"));
				String date = rs.getString("date");

				// add into the html table
				output += "<tr><td>" + paymentId + "</td>";
				output += "<td>" + accountNum + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + date + "</td>";

				// buttons
				output += "<td><input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='paymentId' type='hidden' value='" + paymentId + "'>" + "</form></td></tr>";
			}

			// Complete the html table
			output += "</table>";

		}
		catch (Exception e) {
			
			// error while reading
			output = "Error while reading payment details!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

		
	
	// update payment details
	
	public String updatePayment(String ID, String account, String amount, String date) {
		
		String output = "";		
		
		try {
			
			// database connection
			Connection con = DbConnectionProvider.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET accountNum=?,amount=?,date=?WHERE paymentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, account);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, date);
			preparedStmt.setInt(4, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			
			// update success message
			output = "Updated successfully!";

		}
		catch (Exception e) {
			
			// error while updating
			output = "Error while updating the payment!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	// delete payment details
	
	public String deletePayment(String paymentId) {
		
		String output = "";
		
		try {
			
			// database connection
			Connection con = DbConnectionProvider.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
	
			// create a prepared statement
			String query = "delete from payment where paymentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentId));
	
			// execute the statement
			preparedStmt.execute();
	
			// delete success message
			output = "Deleted successfully!";
	
		}
		catch (Exception e) {
			
			// error while deleting
			output = "Error while deleting the payment!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}	
	
	
	// search payment details
	
	public String searchPayment(String id) {
	
		int convertedID = Integer.parseInt(id);
		String output = "";
		
		try {
			
			// database connection
			Connection con = DbConnectionProvider.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for searching.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment ID</th>" + "<th>Account Number</th>"
						+ "<th>Payment Amount</th>" + "<th>Payment Date</th>" + "<th>Delete</th>";
	
			String query = "select * from payment where accountNum='" + convertedID + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String paymentId = Integer.toString(rs.getInt("paymentId"));
				String accountNum = rs.getString("accountNum");
				String amount = Double.toString(rs.getDouble("amount"));
				String date = rs.getString("date");
	
				// add into the html table
				output += "<tr><td>" + paymentId + "</td>";
				output += "<td>" + accountNum + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + date + "</td>";
	
				// buttons
				output += "<td><input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='paymentId' type='hidden' value='" + paymentId + "'>" + "</form></td></tr>";
			}
	
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e) {
			
			// error while searching
			output = "Error while searching payment details!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
