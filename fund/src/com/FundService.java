package com;

import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {

	Fund fundObj= new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	{
		return fundObj.readFunds();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("FunderCode") String FunderCode, 
							@FormParam("CompanyName") String CompanyName,
							@FormParam("ContactNo") String ContactNo,
							@FormParam("CompanyDesc") String CompanyDesc)
							
	{
		String output = fundObj.insertFund(FunderCode, CompanyName, ContactNo, CompanyDesc);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String fundData)
	{
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	 String FID = fundObject.get("FID").getAsString();
	 String FunderCode = fundObject.get("FunderCode").getAsString();
	 String CompanyName = fundObject.get("CompanyName").getAsString();
	 String ContactNo = fundObject.get("ContactNo").getAsString();
	 String CompanyDesc = fundObject.get("CompanyDesc").getAsString();
	 
	 String output = fundObj.updateFund(FID, FunderCode, CompanyName, ContactNo, CompanyDesc);
	 return output;
	}
		
		
	

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFund(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <ID>
	 String FID = doc.select("FID").text(); 
	 String output = fundObj.deleteFund(FID); 
	return output; 
	}
	
	
	
	
	
	
	
	
	
}
