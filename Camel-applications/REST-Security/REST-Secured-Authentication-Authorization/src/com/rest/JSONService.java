package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/product")
public class JSONService {

	//the secured URL method
	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getSecuredProductJSON() {

		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);		
		return product; 
	}
	
	
	//the open URL method
	@GET
	@Path("/check")
	@Produces("application/json")
	public Product getProduct() {

		Product product = new Product();
		product.setName("Samsung");
		product.setQty(121);
		
		return product; 

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {

		String result = "Product created : " + product;
		return Response.status(201).entity(result).build();
		
	}
	
}