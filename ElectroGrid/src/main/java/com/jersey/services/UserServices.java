package com.jersey.services;

import java.io.IOException;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jersey.bean.OtpBean;

//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import com.jersey.bean.UserBean;
import com.jersey.dao.LoginDao;
import com.jersey.dao.OtpDao;
import com.jersey.dao.UserDao;


@Path("/users")
public class UserServices 
{
	UserDao userdo = new UserDao();
	UserDao userdo2 = new UserDao();
	UserDao userdo3 = new UserDao();
	
	@GET
	@Path("/showdata")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return userdo.readUsers();
	}
	
	
	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(String userdata) throws JsonParseException, JsonMappingException, IOException
	{
		String str=null;
		if(null!=userdata)
		{
		ObjectMapper mapper = new ObjectMapper();
		UserBean userbean = mapper.readValue(userdata, UserBean.class);
		str = UserDao.registerDao(userbean);
		}
		return str;
	}
	
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginUser(String userdata) throws JsonParseException, JsonMappingException, IOException
	{
		String str=null;
		if(null!=userdata)
		{
		ObjectMapper mapper = new ObjectMapper();
		UserBean userbean = mapper.readValue(userdata, UserBean.class);
		str = LoginDao.LoginDao(userbean);
		}
		return str;
	}
	
	
	@Path("/verify")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getVerify(String userdata) throws JsonParseException, JsonMappingException, IOException
	{
		OtpBean o = null;
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(userdata);
		if(jsonElement.isJsonObject())
		{
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			o = new OtpBean(jsonObject.get("otp").getAsString());
		}
		
		OtpDao otpDao = new OtpDao();
		String msg = otpDao.verifyotp(o);
		
		if(msg.equals("User_Activated!!"))
		{
			return "User Activated!!!";
		}
		
		else if(msg.equals("User_Not_Activated!!!"))
		{
			return "User Not Activated";
		}
		
		else
		{
			return "Invalid OTP";
		}
		
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String updateUser(String userdata)
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userdata).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String name = userObject.get("name").getAsString();
		 String email = userObject.get("email").getAsString();
		 String password = userObject.get("pass").getAsString();
		 String mobile = userObject.get("mobile").getAsString();
		 /*String otp = userObject.get("otp").getAsString();
		 String status = userObject.get("status").getAsString();*/		 
		 String output = userdo2.updateTheUser(name, email, password, mobile);
		return output;
	}
	
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String deleteUser(String userdata)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userdata, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String email = doc.select("email").text();
	 String output = userdo3.deleteUser(email);
	return output;
	}

	
}
