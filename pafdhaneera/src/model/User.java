package model;


import java.sql.*;

public class User {

	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	
	
	public String insertUser(int u_ID,String Name, String NIC, String Address,String Mail,int Phone,String Password,String Type)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into user (ID,U_ID,U_Name,U_NIC,U_Address,U_Mail,U_Phone,U_Password,U_Type)"
	 + " values (?, ?, ?, ?, ? , ? , ?, ? ,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1,0);
	 preparedStmt.setInt(2, u_ID);
	 preparedStmt.setString(3, Name);
	 preparedStmt.setString(4, NIC);
	 preparedStmt.setString(5, Address);
	 preparedStmt.setString(6, Mail);
	 preparedStmt.setInt(7, Phone);
	 preparedStmt.setString(8, Password);
	 preparedStmt.setString(9, Type);
	 
	// execute the statement
	//System.out.println(code);
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the user.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	

	public String readUsers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>user id</th><th>user Name</th>" +
	 "<th>user nic</th>" +
	 "<th>user address</th>" +
	 "<th>user mail</th>" +
	 "<th>user phone</th>" +
	 "<th>user password</th>" +
	 "<th>user type</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from user";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 
	 String ID = Integer.toString(rs.getInt("ID"));
	 
	 String U_ID = rs.getString("U_ID");
	 String U_Name = rs.getString("U_Name");
	 String U_NIC = rs.getString("U_NIC");
	 String U_Address = rs.getString("U_Address");
	 String U_Mail = rs.getString("U_Mail");
	 String U_Phone = rs.getString("U_Phone");
	 String U_Password = rs.getString("U_Password");
	 String U_Type = rs.getString("U_Type");
	 
	 
	 // Add into the html table
	 output += "<tr><td>" + U_ID + "</td>";
	 output += "<td>" + U_Name + "</td>";
	 output += "<td>" + U_NIC + "</td>";
	 output += "<td>" + U_Address + "</td>";
	 output += "<td>" + U_Mail  + "</td>";
	 output += "<td>" + U_Phone  + "</td>";
	 output += "<td>" + U_Password + "</td>";
	 output += "<td>" + U_Type + "</td>";
	 
	 
	 // buttons
	 output += "<td><a href='TextBox.jsp' class='btn btn-secondary' value='ID'>update</a></td>" 
	 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + ID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the user.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	
	public String updateUser(String ID,String u_ID,String Name,String NIC, String Address,String Mail,String Phone,String Password,String Type)
	//4
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 
	
	  
	 // create a prepared statement
	 String query = "UPDATE user SET U_ID=?,U_Name=?,U_NIC=?,U_Address=?,U_Mail=?,U_Phone=?,U_Password=?,U_Type=? WHERE ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, u_ID);
	 preparedStmt.setString(2,  Name);
	 preparedStmt.setString(3, NIC);
	 preparedStmt.setString(4, Address);
	 preparedStmt.setString(5, Mail);
	 preparedStmt.setString(6, Phone);
	 preparedStmt.setString(7, Password);
	 preparedStmt.setString(8, Type);
	 preparedStmt.setInt(9, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the user.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String deleteUser(String ID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from user where ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the user.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
	
}
