package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Project;
@Path("/Projects")
public class ProjectService {
	
	 Project projectObj = new Project();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	 {
	 return projectObj.readProjects();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addProject(
	 @FormParam("Project_Name") String Project_Name,
	 @FormParam("Project_About") String Project_About,
	 @FormParam("Project_Context") String Project_Context,
	 @FormParam("Project_Goals") String Project_Goals,
	 @FormParam("Project_Budget") String Project_Budget,
	 @FormParam("Project_Timeline") String Project_Timeline,
	 @FormParam("Team_Name1") String Team_Name1,
	 @FormParam("Team_Dis1") String Team_Dis1,
	 @FormParam("Team_Name2") String Team_Name2,
	 @FormParam("Team_Dis2") String Team_Dis2)
	{
	 String output = projectObj.addProject(Project_Name, Project_About, Project_Context,Project_Goals,Project_Budget,Project_Timeline,Team_Name1,Team_Dis1,Team_Name2,Team_Dis2);
	 return output;
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProject(String productsData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject projectObject = new JsonParser().parse(productsData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String Project_ID = projectObject.get("Project_ID").getAsString(); 
	 String Project_Name =projectObject.get("Project_Name").getAsString();
	 String Project_About =projectObject.get("Project_About").getAsString();
	 String Project_Context = projectObject.get("Project_Context").getAsString(); 
	 String Project_Goals = projectObject.get("Project_Goals").getAsString(); 
	 String Project_Budget = projectObject.get("Project_Budget").getAsString();
	 String Project_Timeline = projectObject.get("Project_Timeline").getAsString();
	 String Team_Name1 = projectObject.get("Team_Name1").getAsString(); 
	 String Team_Dis1 = projectObject.get("Team_Dis1").getAsString(); 
	 String Team_Name2 = projectObject.get("Team_Name2").getAsString(); 
	 String Team_Dis2 = projectObject.get("Team_Dis2").getAsString(); 
	 String output = projectObj.updateProject(Project_ID, Project_Name, Project_About, Project_Context, Project_Goals, Project_Budget, Project_Timeline,Team_Name1,Team_Dis1,Team_Name2,Team_Dis2); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String productsData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productsData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String Project_ID = doc.select("Project_ID").text(); 
	 String output = projectObj.deleteProject(Project_ID); 
	return output;
	
	}
	
	}

