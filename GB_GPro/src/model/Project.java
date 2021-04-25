package model;
import java.sql.*;

public class Project {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

	 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_project","root" ,"");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
public String addProject(String Project_Name, String Project_About, String Project_Context, String Project_Goals, String Project_Budget, String Project_Timeline, String Team_Name1, String Team_Dis1, String Team_Name2, String Team_Dis2 )
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 	String query ="insert into projects values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Project_Name);
		 preparedStmt.setString(3, Project_About);
		 preparedStmt.setString(4, Project_Context);
		 preparedStmt.setString(5, Project_Goals);
		 preparedStmt.setDouble(6, Double.parseDouble(Project_Budget));
		 preparedStmt.setString(7, Project_Timeline);
		 preparedStmt.setString(8, Team_Name1);
		 preparedStmt.setString(9, Team_Dis1);
		 preparedStmt.setString(10, Team_Name2);
		 preparedStmt.setString(11, Team_Dis2);
		 //execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the Project.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readProjects()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	
	 output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
			  "<th>Project About</th>" +
			  "<th>Project Context</th>" +
			  "<th>Project Goals</th>" +
			  "<th>Project Budget</th>" +
			  "<th>Project Timeline</th>" +
			  "<th>Project Team Member</th>" +
			  "<th>Project Member Discription</th>" +
			  "<th>Project Team Member</th>" +
			  "<th>Project Member Discription</th>" +
			  "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from projects";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String Project_ID = Integer.toString(rs.getInt("Project_ID"));
	 String Project_Name = rs.getString("Project_Name");
	 String Project_About = rs.getString("Project_About");
	 String Project_Context = rs.getString("Project_Context");
	 String Project_Goals = rs.getString("Project_Goals");
	 String Project_Budget = Double.toString(rs.getDouble("Project_Budget"));
	 String Project_Timeline = rs.getString("Project_Timeline");
	 String Team_Name1 = rs.getString("Team_Name1");
	 String Team_Dis1 = rs.getString("Team_Dis1");
	 String Team_Name2 = rs.getString("Team_Name2");
	 String Team_Dis2 = rs.getString("Team_Dis2");
	 
	 output += "<tr><td>" + Project_ID + "</td>";
	 output += "<td>" + Project_Name + "</td>";
	 output += "<td>" + Project_About + "</td>";
	 output += "<td>" + Project_Context + "</td>";
	 output += "<td>" + Project_Goals + "</td>";
	 output += "<td>" + Project_Budget + "</td>";
	 output += "<td>" + Project_Timeline + "</td>";
	 output += "<td>" + Team_Name1 + "</td>";
	 output += "<td>" + Team_Dis1 + "</td>";
	 output += "<td>" + Team_Name2 + "</td>";
	 output += "<td>" + Team_Dis2 + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='projects.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='Project_ID' type='hidden' value='" + Project_ID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the projects.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateProject(String Project_ID, String Project_Name, String Project_About, String Project_Context, String Project_Goals, String Project_Budget, String Project_Timeline, String Team_Name1, String Team_Dis1, String Team_Name2, String Team_Dis2)
	
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE projects SET Project_Name=?,Project_About=?,Project_Context=?,Project_Goals=?,Project_Budget=?,Project_Timeline=?,Team_Name1=?,Team_Dis1=?,Team_Name2=?,Team_Dis2=? WHERE Project_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, Project_Name);
		 preparedStmt.setString(2, Project_About);
		 preparedStmt.setString(3, Project_Context);
		 preparedStmt.setString(4, Project_Goals);
		 preparedStmt.setDouble(5, Double.parseDouble(Project_Budget));
		 preparedStmt.setString(6, Project_Timeline);
		 preparedStmt.setString(7, Team_Name1);
		 preparedStmt.setString(8, Team_Dis1);
		 preparedStmt.setString(9, Team_Name2);
		 preparedStmt.setString(10, Team_Dis2);
		 preparedStmt.setInt(11, Integer.parseInt(Project_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the Project.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteProject(String Project_ID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from projects where Project_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(Project_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the project.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		} 


