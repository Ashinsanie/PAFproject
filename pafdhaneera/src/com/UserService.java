package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;


@Path("/Users")
public class UserService {
	
	User userObj = new User();
	
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return userObj.readUsers();
	 }
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
	@FormParam("U_ID") int U_ID,		
	 @FormParam("U_Name") String U_Name,
	 @FormParam("U_NIC") String U_NIC,
	 @FormParam("U_Address") String U_Address,
	 @FormParam("U_Mail") String U_Mail,
	@FormParam("U_Phone") int U_Phone,
   @FormParam("U_Password") String U_Password,
   @FormParam("U_Type") String U_Type)
	{
	 String output = userObj.insertUser( U_ID,U_Name, U_NIC, U_Address, U_Mail,U_Phone,U_Password,U_Type);
	return output;
	}
	
	
	
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
		
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = itemObject.get("ID").getAsString();
	 String U_ID = itemObject.get("U_ID").getAsString();
	 String U_Name = itemObject.get("U_Name").getAsString();
	 String U_NIC = itemObject.get("U_NIC").getAsString();
	 String U_Address = itemObject.get("U_Address").getAsString();
	 String U_Mail = itemObject.get("U_Mail").getAsString();
	 String U_Phone = itemObject.get("U_Phone").getAsString();
	 String U_Password = itemObject.get("U_Password").getAsString();
	 String U_Type = itemObject.get("U_Type").getAsString();
	
	 
	 String output = userObj.updateUser(ID, U_ID, U_Name, U_NIC, U_Address,U_Mail,U_Phone,U_Password,U_Type);
	return output;
	}
	


	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = userObj.deleteUser(ID);
	return output;
	}


}
