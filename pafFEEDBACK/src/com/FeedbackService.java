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

import model.Feedback;


@Path("/Feedbacks")
public class FeedbackService {

	

	
	Feedback feedbackObj = new Feedback();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFeedback()
	 {
	 return feedbackObj.readFeedback();
	 }
	
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFeedback(
	 @FormParam("cusName") String cusName,
	 @FormParam("cusMail") String cusMail,
	 @FormParam("cusPhone") int cusPhone,
	 @FormParam("comment") String comment)
	{
	 String output = feedbackObj.insertFeedback(cusName,cusMail,cusPhone,comment);
	return output;
	}
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFeedback(String feedbackData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(feedbackData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String fID = customerObject.get("fID").getAsString();
	 String cusName = customerObject.get("cusName").getAsString();
	 String cusMail= customerObject.get("cusMail").getAsString();
	 String cusPhone = customerObject.get("cusPhone").getAsString();
	 String comment= customerObject.get("comment").getAsString();
	 String output = feedbackObj.updateFeedback(fID,cusName,cusMail,cusPhone,comment);
	return output;
	}
	
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData)
	{
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String fID = doc.select("fID").text();
	 String output = feedbackObj.deleteFeedback(fID);
	return output;
	}

}
