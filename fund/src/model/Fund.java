package model;

import java.sql.*;

public class Fund {

	
	//A common method to connect to the DB
		private Connection connect() 
		{ 
			
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.cj.jdbc.Driver"); 
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funder", "root", ""); 
			} 
			catch (Exception e) 
			{e.printStackTrace();} 
			return con; 
		}
		
		public String insertFund(String FunderCode, String CompanyName, String ContactNo, String CompanyDesc)
		{
			String output = "";
			try 
			{
				Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for inserting."; 
				 }
				 
				// create a prepared statement
				 String query = "insert into funder values (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 	
				 	// binding values
				 	preparedStmt.setInt(1,0);
				 	preparedStmt.setString(2, FunderCode);
				 	preparedStmt.setString(3, CompanyName);
				 	preparedStmt.setInt(4, Integer.parseInt(ContactNo));
				 	preparedStmt.setString(5, CompanyDesc);
				 	//preparedStmt.setString(6, CompanyAddress);
				 	
				 	
				 // execute the statement
				 	preparedStmt.execute();
				 	con.close();
				 	
				 	output = "Inserted successfully";
			}
			catch (Exception e) 
			{
				output = "Error while inserting details.";
				 System.err.println(e.getMessage());
			}
			return output; 
		}
		
		public String readFunds()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				// Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Funder Code</th><th>Company Name</th>" +
						 "<th>Contact Number</th>" +
						 "<th>Company Desc</th>" +
						 //"<th>CompanyAddress</th>" +
						 "<th>Update</th><th>Remove</th></tr>";
				 
				 String query = "select * from funder";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				// iterate through the rows in the result set
				 while (rs.next())
				 {
					 String FID = Integer.toString(rs.getInt("FID"));
					 String FunderCode = rs.getString("FunderCode");
					 String CompanyName = rs.getString("CompanyName");
					 String ContactNo = Integer.toString(rs.getInt("ContactNo"));
					 String CompanyDesc = rs.getString("CompanyDesc");
					 //String CompanyAddress = rs.getString("CompanyAddress");
				 
					 // Add into the html table
					 output += "<tr><td>" + FunderCode + "</td>";
					 output += "<td>" + CompanyName + "</td>";
					 output += "<td>" + ContactNo + "</td>";
					 output += "<td>" + CompanyDesc + "</td>";
					 //output += "<td>" + CompanyAddress + "</td>";
					 
				 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 	+ "<td><form method='post' action='funders.jsp'>"
						 	+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 	+ "<input name='FID' type='hidden' value='" + FID 
						 	+ "'>" + "</form></td></tr>";
				 }
				 con.close();
				 
				 // Complete the html table
				 output += "</table>";
			}
			catch  (Exception e)
			{
				output = "Error while reading the details.";
				 System.err.println(e.getMessage());
			}
			return output; 
		}
		
		public String updateFund(String FID, String FunderCode, String CompanyName, String ContactNo, String CompanyDesc)
		{
			String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {		
					 return "Error while connecting to the database for updating."; 
				 }
				 
				 
				 // create a prepared statement
				 String query = "UPDATE funder SET FunderCode=?,CompanyName=?,ContactNo=?,CompanyDesc=? WHERE FID=?"; 
		 		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 		 
				 // binding values
		 		 preparedStmt.setString(1, FunderCode);
		 		 preparedStmt.setString(2, CompanyName);
		 		 preparedStmt.setInt(3, Integer.parseInt(ContactNo));
		 		 preparedStmt.setString(4, CompanyDesc);
		 		 preparedStmt.setInt(5, Integer.parseInt(FID));
			 	
			 	// execute the statement
			 	preparedStmt.execute();
			 	con.close();
			 	
			 	output = "Updated successfully";
		 		 
			 }
			 catch(Exception e)
			 {
				 output = "Error while updating the details."; 
		 		 System.err.println(e.getMessage());  
			 }
			 return output; 
		}
		
		public String deleteFund(String FID)
		 {
			String output = "";
			
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
			
				// create a prepared statement
				String query = "delete from funder where FID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(FID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the details.";
				System.err.println(e.getMessage());
			}
			 
			return output;
		
		 }
}
