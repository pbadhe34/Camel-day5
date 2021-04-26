package com.app;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;

/**
 * Setup Jetty security to use basic authentication for all the paths.
 * <p/>
 * In a real use-case --> use more stronger security or integrate to a LDAP or some other use-store.
 * Here we load the users from a file.
 * Securiyy constrraints
 * Only as user for getting all customer
 * Only as customer and admin for posting new customer
 *  Only as admin for putting/updating exiting customer
 *   Only as admin for deleting current customer
 */
public class JettySecurity {

	
	 //http://localhost:8080/orders/get/1
    
    //http://localhost:8080/orders/all
    
    //http://localhost:8080/orders/get
	
	
    public static ConstraintSecurityHandler createSecurityHandler() {
		
		  Constraint userConstraint = new Constraint("BASIC", "user");
		  userConstraint.setAuthenticate(true);
		  userConstraint.setRoles(new String[] {"user"});
		  ConstraintMapping userMapping = new
		  ConstraintMapping(); 
		  userMapping.setConstraint(userConstraint);
		  userMapping.setMethod("GET"); 
		 // userMapping.setPathSpec("/get/*");
		  userMapping.setPathSpec("/*");
		  userMapping.setMethodOmissions(new String[] {"/add"});
		  
		  // http://localhost:8080/orders/add/1	
		  
			 
			 
		  //http://localhost:8080/orders/get/1
		  
		  Constraint custConstraint = new Constraint("BASIC", "customer");
		  custConstraint.setAuthenticate(true); 
		  custConstraint.setRoles(new String[] {"customer"});
		  
		  ConstraintMapping custMapping = new
		  ConstraintMapping(); 
		 
		  custMapping.setConstraint(custConstraint);
		  custMapping.setMethod("GET"); 
		  custMapping.setPathSpec("/add/*");
		//  custMapping.setPathSpec("/*");
	      custMapping.setMethodOmissions(new String[] {"/get","/all"});
		  
		  
		  ConstraintSecurityHandler cshandler = new ConstraintSecurityHandler();
		  cshandler.addConstraintMapping(userMapping);
		  cshandler.addConstraintMapping(custMapping); 
		  cshandler.setAuthenticator(new  BasicAuthenticator());
		 
		/*
		 * An Authenticator is responsible for checking requests and sending response
		 * challenges in order to authenticate a request. Various types of
		 * Authentication are returned in order to signal the next step in
		 * authentication.
		 */
    	
		/*
		 * final Constraint userConstraint = new Constraint();
		 * userConstraint.setName(Constraint.__BASIC_AUTH); userConstraint.setRoles(new
		 * String[] {"user"}); userConstraint.setAuthenticate(true); final
		 * ConstraintMapping userMapping = new ConstraintMapping();
		 * userMapping.setConstraint(userConstraint); userMapping.setMethod("GET"); //
		 * userMapping.setPathSpec("/get/*"); userMapping.setPathSpec("/get/*");
		 * 
		 * 
		 * Constraint custConstraint = new Constraint();
		 * userConstraint.setName(Constraint.__BASIC_AUTH); custConstraint.setRoles(new
		 * String[] {"customer"}); custConstraint.setAuthenticate(true);
		 * ConstraintMapping custMapping = new ConstraintMapping();
		 * custMapping.setConstraint(custConstraint); custMapping.setMethod("GET");
		 * custMapping.setPathSpec("/add/*");
		 * 
		 * //org.mortbay.jetty.UserRealm
		 */
		 
		   
         
			/*
			 * Constraint disableTraceConstraint = new Constraint();
			 * disableTraceConstraint.setName("Disable TRACE");
			 * disableTraceConstraint.setAuthenticate(true);
			 * 
			 * ConstraintMapping mapping = new ConstraintMapping();
			 * mapping.setConstraint(disableTraceConstraint); mapping.setMethod("TRACE");
			 * mapping.setPathSpec("/");
			 */
	        // OomissionConstraint is to fix the warning log ""null has uncovered http methods for path: /
	        // No impact to disable TRACE if you do not add this constraint
	        // But if you're using the monitoring tool like Geneos, and your component requires keep production monitoring all green,
	        // You can try to add this omissionConstraint to fix the warning Jetty prints.
			/*
			 * Constraint omissionConstraint = new Constraint(); ConstraintMapping
			 * omissionMapping = new ConstraintMapping();
			 * omissionMapping.setConstraint(omissionConstraint);
			 * omissionMapping.setMethod("*"); omissionMapping.setPathSpec("/");
			 */

	        ConstraintSecurityHandler handler = new ConstraintSecurityHandler();
			/*
			 * handler.addConstraintMapping(mapping);
			 * handler.addConstraintMapping(omissionMapping);
			 */
	       
       //  org.mortbay.jetty.plus.jaas.JAASUserRealm jas =new org.mortbay.jetty.plus.jaas.JAASUserRealm();
       
         handler.setRealmName("JettyServer.REALM");        
         handler.setConstraintMappings(new ConstraintMapping[] {custMapping,userMapping});       
         handler.setAuthenticator(new BasicAuthenticator());
         final HashLoginService service = new HashLoginService("RiderAutoParts", "src/main/resources/users.properties");
         handler.setLoginService(service);

        return handler;
    }

}
