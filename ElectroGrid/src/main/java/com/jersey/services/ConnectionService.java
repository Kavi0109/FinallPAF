package com.jersey.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.jersey.bean.reqConnection;

@Path("/Connections")
public class ConnectionService {
	
	reqConnection connectionObj = new reqConnection();
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() 
	 {
		return connectionObj.readConnections(); 
	 } 
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConnection(@FormParam("customerName") String customerName,
	 @FormParam("connectionType") String connectionType,
	 @FormParam("requestLoad") String requestLoad,
	 @FormParam("contractDemand") String contractDemand,
	 @FormParam("address") String address,
	 @FormParam("email") String email)
	{
		 String output = connectionObj.insertConnection(customerName, connectionType, requestLoad, contractDemand, address, email);
		return output;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConnection(String connectionData)
	{
		//Convert the input string to a JSON object
		 JsonObject connectionObject = new JsonParser().parse(connectionData).getAsJsonObject();
		//Read the values from the JSON object
		 String connectionID = connectionObject.get("connectionID").getAsString();
		 String customerName = connectionObject.get("customerName").getAsString();
		 String connectionType = connectionObject.get("connectionType").getAsString();
		 String requestLoad = connectionObject.get("requestLoad").getAsString();
		 String contractDemand = connectionObject.get("contractDemand").getAsString();
		 String address = connectionObject.get("address").getAsString();
		 String email = connectionObject.get("email").getAsString();
		 String output = connectionObj.updateConnection(connectionID,customerName, connectionType, requestLoad, contractDemand, address, email);
		return output;
	}
	
	
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConnection(String connectionData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(connectionData, "", Parser.xmlParser());
	
		//Read the value from the element <connectionID>
		 String connectionID = doc.select("connectionID").text();
		 String output = connectionObj.deleteConnection(connectionID);
		return output;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String readConnections(@PathParam("id") String id) 
	 {
		return connectionObj.readConnections(id); 
	 }
	
	
 

	

}
