package com.jersey.services;
import com.jersey.bean.*;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Bills")
public class BillingService
{
	Billing BillObj = new Billing();
	
	//Read the bills
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String readBills()
	 {
		return BillObj.readBills();
	 }


	//Insert the bills
		@POST
		@Path("/post")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBills(@FormParam("BillCode") String BillCode,
								 @FormParam("BillMonth") String BillMonth,
								 @FormParam("CurrentRead") int CurrentRead,
								 @FormParam("PreviousRead") int PreviousRead,
								 @FormParam("TotalUnits") int TotalUnits,
								 @FormParam("FinalAmount") String FinalAmount)
		{
			 String output = BillObj.insertBills(BillCode, BillMonth, CurrentRead, PreviousRead,TotalUnits,FinalAmount); 
			 return output;
		}
		
		
		
	
	//Update the bills
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateBills(String BillData)
	{
		//Convert the input string to a JSON object
		 JsonObject BillObject = new JsonParser().parse(BillData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String BillID = BillObject.get("BillID").getAsString();
		 String BillCode = BillObject.get("BillCode").getAsString();
		 String BillMonth = BillObject.get("BillMonth").getAsString();
		 String CurrentRead = BillObject.get("CurrentRead").getAsString();
		 String PreviousRead = BillObject.get("PreviousRead").getAsString();
		 String TotalUnits = BillObject.get("TotalUnits").getAsString();
		 String FinalAmount = BillObject.get("FinalAmount").getAsString();
		 
		
		 String output = BillObj.UpdateBills(BillID, BillCode, BillMonth, CurrentRead, PreviousRead,TotalUnits,FinalAmount);
		 
		return output;
		 
	}
	
	
	
	
	//Delete the bills
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBills(String BillData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		 String BillID = doc.select("BillID").text();
		 String output = BillObj.deleteBills(BillID);
		return output;
	}
	
	
}