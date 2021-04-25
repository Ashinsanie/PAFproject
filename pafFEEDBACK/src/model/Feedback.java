package model;

import java.sql.*;

public class Feedback {

	// connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetb", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		public String insertFeedback(String cname, String cmail, int cphone , String com)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 
		 // create a prepared statement
		 String query = " insert into feedback (fID,cusName,cusMail,cusPhone,comment)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1,0);
		 preparedStmt.setString(2, cname);
		 preparedStmt.setString(3, cmail);
		 preparedStmt.setInt(4, cphone);
		 preparedStmt.setString(5, com);
		 
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the comment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		public String readFeedback()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>customer name</th><th>customer mail</th>" +
		 "<th>contact number</th>" +
		 "<th>comment</th>"+
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from feedback";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String fID = Integer.toString(rs.getInt("fID"));
		 String cusName= rs.getString("cusName");
		 String cusMail = rs.getString("cusMail");
		 String cusPhone = Integer.toString(rs.getInt("cusPhone"));
		 String comment = rs.getString("comment");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + cusName + "</td>";
		 output += "<td>" + cusMail + "</td>";
		 output += "<td>" + cusPhone  + "</td>";
		 output += "<td>" + comment + "</td>";
		 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='feedback.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + fID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the comment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		//update
		public String updateFeedback(String iD, String cname, String cmail, String cphone, String com)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 
		 // create a prepared statement
		 String query = "UPDATE feedback SET cusName=?,cusMail=?,cusPhone=?,comment=? WHERE fID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, cname);
		 preparedStmt.setString(2, cmail);
		 preparedStmt.setDouble(3,Double.parseDouble(cphone));
		 preparedStmt.setString(4, com);
		 preparedStmt.setInt(5, Integer.parseInt(iD));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the comment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//deletion
		public String deleteFeedback(String fID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from feedback where fID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the comment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
}
