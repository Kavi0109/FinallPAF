package com.jersey.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.dbconn.DbConnectionProvider;


@Path("/test")
public class Test 
{
	@Path("/show")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String show() 
	{
		return "test app"+DbConnectionProvider.getConnection();
	}
	
	@Path("/disp")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String disp() 
	{
		return "test disp"+DbConnectionProvider.getConnection();
	}
}
