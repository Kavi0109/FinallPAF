package com.jersey.services;

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
import com.jersey.bean.Payment;

@Path("/Pay")
public class PaymentService {

	Payment payObj = new Payment();

	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_PLAIN)
	public String readPayment() {
		return payObj.readPayment();
	}

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

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String paymentId = paymentObject.get("paymentId").getAsString();
		String accountNum = paymentObject.get("accountNum").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String date = paymentObject.get("date").getAsString();

		String output = payObj.updatePayment(paymentId, accountNum, amount, date);
		return output;
	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String paymentId = doc.select("paymentId").text();
		String output = payObj.deletePayment(paymentId);
		return output;
	}

}
