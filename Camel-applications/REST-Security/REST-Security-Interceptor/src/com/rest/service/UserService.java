package com.rest.service;
 
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.rest.data.UserDatabase;
import com.rest.model.User;

//http://localhost:8080/REST-Security-Interceptor/user-service/users/1  with GET
//http://localhost:8080/REST-Security-Interceptor/user-service/users/1  with PUT method

//http://localhost:8080/REST-Security-Interceptor/user-service/check/1   with GET method
 
@Path("/user-service")
public class UserService
{
	@PermitAll
    @GET
    @Path("/users/{id}")
    public  User getUserById(@PathParam("id") int id, @Context Request req)  //Response
    {
		System.out.println("UserService.getUserById() called here");
        Response.ResponseBuilder rb = Response.ok(UserDatabase.getUserById(id));
        String res = rb.build().toString();
      //  System.out.println("The user response is  "+res);
       // return rb.build();
        User user = UserDatabase.getUserById(id);
        return user;
    }
     
	@RolesAllowed("ADMIN")
    @PUT
    @Path("/users/{id}")
    public Response updateUserById(@PathParam("id") int id)
    {
		System.out.println("UserService.put  updateUserById() called here ");
        //Update the User resourcecalled herealled here
        UserDatabase.updateUser(id);
        return Response.status(200).build();
    }
	
	//@RolesAllowed("ADMIN")
	//@PermitAll
	@DenyAll
    @GET
    @Path("/check/{id}")
    public User  getUpdateUser(@PathParam("id") int id) //Response
    {
		System.out.println("UserService check .getUpdateUser() called here");
		 Response.ResponseBuilder rb = Response.ok(UserDatabase.getUserById(id));
	   // return rb.build();
	     
	     User user = UserDatabase.getUserById(id);
	        return user;
    }
}