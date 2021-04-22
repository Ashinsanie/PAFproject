package com;

import model.Product; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Products") 
public class ProductService {
	
	Product productObj = new Product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	 { 
		 return productObj.readProducts();  
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(@FormParam("ProductCode") String ProductCode, 
	 @FormParam("ProductCategory") String ProductCategory,
	 @FormParam("ProductName") String ProductName, 
	 @FormParam("ProductPrice")String ProductPrice, 
	 @FormParam("ProductDesc") String ProductDesc) 
	{ 
	 String output = productObj.insertProduct(ProductCode,ProductCategory,ProductName, ProductPrice, ProductDesc); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String productsData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject productsObject = new JsonParser().parse(productsData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ProductID = productsObject.get("ProductID").getAsString(); 
	 String ProductCode =productsObject.get("ProductCode").getAsString();
	 String ProductCategory =productsObject.get("ProductCategory").getAsString();
	 String ProductName = productsObject.get("ProductName").getAsString(); 
	 String ProductPrice = productsObject.get("ProductPrice").getAsString(); 
	 String ProductDesc = productsObject.get("ProductDesc").getAsString(); 
	 String output = productObj.updateProduct(ProductID, ProductCode, ProductCategory, ProductName, ProductPrice, ProductDesc); 
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
	 String ProductID = doc.select("ProductID").text(); 
	 String output = productObj.deleteProduct(ProductID); 
	return output; 
	}

	

}
