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
import com.jersey.bean.Payment;

@Path("/Pay")
public class PaymentService {

	Payment payObj = new Payment();

	// read payment details
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {		
		return payObj.readPayment();
	}

	// insert payment details
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("accountNum") String accountNum,
								@FormParam("amount") String amount,
								@FormParam("date") String date) {						
								String output = payObj.insertPayment(accountNum, amount, date);
								return output;
							}

	// update payment details
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {
		
		// convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		
		// read the values from the JSON object
		String paymentId = paymentObject.get("paymentId").getAsString();
		String accountNum = paymentObject.get("accountNum").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String date = paymentObject.get("date").getAsString();

		String output = payObj.updatePayment(paymentId, accountNum, amount, date);
		return output;
	}

	// delete payment details
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		
		// convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		
		// read the value from the element <paymentId>
		String paymentId = doc.select("paymentId").text();
		String output = payObj.deletePayment(paymentId);
		return output;
	}
	
	// search payment details
	@GET
	@Path("search/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String searchPayment(@PathParam("id") String id) 
	 {
		return payObj.searchPayment(id); 
	 }

}
