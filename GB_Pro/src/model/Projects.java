package model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
 
public class Projects implements IProjects{

			@Override
			public int addProject(com.ProjectsService pr) {
				int xid=0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="insert into projects (Project_Name,Project_About,Project_Context,Project_Goals,Project_Budget,Project_Timeline,Team_Name1,Team_Dis1,Team_Name2,Team_Dis2) values('"+pr.getName()+"','"+pr.getAbout()+"','"+pr.getContext()+"','"+pr.getGoals()+"','"+pr.getBudget()+"','"+pr.getTimeline()+"','"+pr.getName1()+"','"+pr.getDis1()+"','"+pr.getName2()+"','"+pr.getDis2()+"')";  
					Statement st= (Statement) con.createStatement();
					st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = st.getGeneratedKeys();
			        if (rs.next()) {
			             xid = rs.getInt(1);
			        }
					
					con.close();
					return xid;
					
				} catch (ClassNotFoundException |SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
				
			}

			@Override
			public void removeProject(int id){
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="update projects set Status = 'del' where Project_ID = " +id; 
					Statement st= (Statement) con.createStatement();
					st.execute(sql);
					con.close();
				
					
				} catch (ClassNotFoundException |SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}

			@Override
			public com.ProjectsService getProById(int id) {
				com.ProjectsService Pro=new com.ProjectsService();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="select * from projects where Project_ID = "+id;  
					Statement st= (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if(rs.next()) {
					
						Pro.setId(id);
						Pro.setName(rs.getString("Project_Name"));
						Pro.setAbout(rs.getString("Project_About"));
						Pro.setContext(rs.getString("Project_Context"));
						Pro.setGoals(rs.getString("Project_Goals"));
						Pro.setBudget(rs.getDouble("Project_Budget"));
						Pro.setTimeline(rs.getString("Project_Timeline"));
						Pro.setName1(rs.getString("Team_Name1"));
						Pro.setDis1(rs.getString("Team_Dis1"));
						Pro.setName2(rs.getString("Team_Name2"));
						Pro.setDis2(rs.getString("Team_Dis2"));
					}
					
					con.close();
				
					
				} 
				
				catch (ClassNotFoundException |SQLException e) 
				{
					e.printStackTrace();
				}
				
				return Pro;
			}

			@Override
			public List<com.ProjectsService> getAllPro() {
				List<com.ProjectsService> PL = new ArrayList<com.ProjectsService>();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="select * from projects where Status = 'active'";  
					Statement st= (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					while(rs.next())
					{
						com.ProjectsService P = new com.ProjectsService();
						P.setId(Integer.parseInt(rs.getString("Project_ID")));
						P.setName(rs.getString("Project_Name"));
						P.setAbout(rs.getString("Project_About"));
						P.setContext(rs.getString("Project_Context"));
						P.setGoals(rs.getString("Project_Goals"));
						P.setBudget(rs.getDouble("Project_Budget"));
						P.setTimeline(rs.getString("Project_Timeline"));
						P.setName1(rs.getString("Team_Name1"));
						P.setDis1(rs.getString("Team_Dis1"));
						P.setName2(rs.getString("Team_Name2"));
						P.setDis2(rs.getString("Team_Dis2"));	
						
						PL.add(P);
					}
					
					con.close();
				
					
				} 
				
				catch (ClassNotFoundException |SQLException e) 
				{
					e.printStackTrace();
				}
				
				return PL;
				
			}

			@Override
			public void updateProject(com.ProjectsService p) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="update projects set Project_Name = '"+p.getName()+"', Project_About = '"+p.getAbout()+"',Project_Context = '"+p.getContext()+"',Project_Goals = '"+p.getGoals()+"', Project_Budget ='"+p.getBudget()+"', Project_Timeline ='"+p.getTimeline()+"', Project_Name1 = '"+p.getName1()+"', Project_Dis1='"+p.getDis1()+"', Project_Name2 = '"+p.getName2()+"', Project_Dis = '"+p.getDis2()+"' where Project_ID = "+p.getId(); 
					Statement st= (Statement) con.createStatement();
					st.execute(sql);
					con.close();
				
					
				} catch (ClassNotFoundException |SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}

			public List<com.ProjectsService> getAllDPro() {
				List<com.ProjectsService> PL = new ArrayList<com.ProjectsService>();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_project","root" ,"");
					String sql="select * from projects where Status = 'del'";  
					Statement st= (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					while(rs.next())
					{
						com.ProjectsService P = new com.ProjectsService();
						P.setId(Integer.parseInt(rs.getString("Project_ID")));
						P.setName(rs.getString("Project_Name"));
						P.setAbout(rs.getString("Project_About"));
						P.setContext(rs.getString("Project_Context"));
						P.setGoals(rs.getString("Project_Goals"));
						P.setBudget(rs.getDouble("Project_Budget"));
						P.setTimeline(rs.getString("Project_Timeline"));
						P.setName1(rs.getString("Team_Name1"));
						P.setDis1(rs.getString("Team_Dis1"));
						P.setName2(rs.getString("Team_Name2"));
						P.setDis2(rs.getString("Team_Dis2"));
						
						
						PL.add(P);
					}
					
					con.close();
				
					
				} 
				
				catch (ClassNotFoundException |SQLException e) 
				{
					e.printStackTrace();
				}
				
				return PL;
				
			}

		

	}



