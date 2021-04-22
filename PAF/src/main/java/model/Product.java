package model;

import java.sql.*;

public class Product {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Productdb", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	} 
	
	
	public String insertProduct(String ProductCode,String ProductCategory,String ProductName, String ProductPrice, String ProductDesc) 
	 { 
			String output = ""; 
	 try
	 { 
		 	Connection con = connect(); 
		 	
		 	if (con == null) 
	 		{return "Error while connecting to the database for inserting."; } 
		 	
		 	// create a prepared statement
		 	String query = " insert into product values (?, ?, ?, ?, ?,?)"; 
		 	
		 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 	
		 	// binding values
		 	preparedStmt.setInt(1, 0); 
		 	preparedStmt.setString(2, ProductCode);
		 	preparedStmt.setString(3, ProductCategory);
		 	preparedStmt.setString(4, ProductName); 
		 	preparedStmt.setDouble(5, Double.parseDouble(ProductPrice)); 
		 	preparedStmt.setString(6, ProductDesc);
		 	
		 	// execute the statement
		 	preparedStmt.execute(); 
		 	con.close(); 
		 	output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while inserting the product."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	
	public String readProducts() 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 
	 output = "<table border='1'>"
			+"<tr><th>Product Code</th>"
			+ "<th>Product Category</th>" 
	 		+ "<th>Product Name</th>"
	 		+ "<th>Product Price</th>" 
	 		+ "<th>Product Description</th>"
	 		+ "<th>Update</th><th>Remove</th></tr>"; 
	 
	 	String query = "select * from product"; 
	 	Statement stmt = con.createStatement(); 
	 	ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 	 while (rs.next()) 
	 	 { 
	 	 String ProductID = Integer.toString(rs.getInt("ProductID")); 
	 	 String ProductCode = rs.getString("ProductCode");
	 	 String ProductCategory = rs.getString("ProductCategory");
	 	 String ProductName = rs.getString("ProductName"); 
	 	 String ProductPrice = Double.toString(rs.getDouble("ProductPrice")); 
	 	 String ProductDesc = rs.getString("ProductDesc"); 
	 	 // Add into the html table
	 	 output += "<td>" + ProductCode + "</td>"; 
	 	 output += "<td>" + ProductCategory + "</td>"; 
	 	 output += "<td>" + ProductName + "</td>"; 
	 	 output += "<td>" + ProductPrice + "</td>"; 
	 	 output += "<td>" + ProductDesc + "</td>"; 
	 	 // buttons
	 	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 	 + "<td><form method='post' action='product.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 	 + "<input name='ProductID' type='hidden' value='" + ProductID 
	 	 + "'>" + "</form></td></tr>"; 
	 	 } 
	 	 con.close(); 
	 	 
	 	// Complete the html table
	 	 output += "</table>"; 
	 	 } 
	 	 	catch (Exception e) 
	 	 { 
	 	 		output = "Error while reading the product."; 
	 	 		System.err.println(e.getMessage()); 
	 	 } 
	 	 	return output; 
	 	 } 
	
	
	
	
	 	public String updateProduct(String ProductID,String ProductCode,String ProductCategory, String ProductName, String ProductPrice, String ProductDesc)
	 
	 	{ 
	 		 String output = ""; 
	 		 try
	 		 { 
	 		 Connection con = connect(); 
	 		 if (con == null) 
	 		 {return "Error while connecting to the database for updating."; } 
	 		 // create a prepared statement
	 		 String query = "UPDATE product SET ProductCode=?,ProductCategory=?,ProductName=?,ProductPrice=?,ProductDesc=? WHERE ProductID=?"; 
	 		 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 		 // binding values
	 		 preparedStmt.setString(1, ProductCode);
	 		 preparedStmt.setString(2, ProductCategory);
	 		 preparedStmt.setString(3, ProductName); 
	 		 preparedStmt.setDouble(4, Double.parseDouble(ProductPrice)); 
	 		 preparedStmt.setString(5, ProductDesc); 
	 		 preparedStmt.setInt(6, Integer.parseInt(ProductID)); 
	 		 // execute the statement
	 		 preparedStmt.execute(); 
	 		 con.close(); 
	 		 output = "Updated successfully"; 
	 		 } 
	 		 catch (Exception e) 
	 		 { 
	 		 output = "Error while updating the Product."; 
	 		 System.err.println(e.getMessage()); 
	 		 } 
	 		 return output; 
	 		 } 
	 	
	 	public String deleteProduct(String ProductID) 
	 	 { 
	 	 String output = ""; 
	 	 try
	 	 { 
	 	 Connection con = connect(); 
	 	 if (con == null) 
	 	 {return "Error while connecting to the database for deleting."; } 
	 	 // create a prepared statement
	 	 String query = "delete from product where ProductID=?"; 
	 	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 	 // binding values
	 	 preparedStmt.setInt(1, Integer.parseInt(ProductID)); 
	 	 // execute the statement
	 	 preparedStmt.execute(); 
	 	 con.close(); 
	 	 output = "Deleted successfully"; 
	 	 } 
	 	 catch (Exception e) 
	 	 { 
	 	 output = "Error while deleting the Product."; 
	 	 System.err.println(e.getMessage()); 
	 	 } 
	 	 return output; 
	 	 }
}
	


