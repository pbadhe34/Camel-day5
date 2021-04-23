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

    public static ConstraintSecurityHandler createSecurityHandler() {
        Constraint userConstraint = new Constraint("BASIC", "user");        
        userConstraint.setAuthenticate(true);
         
        
        ConstraintMapping userMapping = new ConstraintMapping();
        userMapping.setConstraint(userConstraint);        
        userMapping.setMethod("GET");
       // userMapping.setPathSpec("/get/*");
        userMapping.setPathSpec("/*");
        
        //http://localhost:8080/orders/get/1
        
        //http://localhost:8080/orders/all
        
        //http://localhost:8080/orders/get
        
         Constraint custConstraint = new Constraint("BASIC", "customer");	        
         custConstraint.setAuthenticate(true);
         //custConstraint.setRoles(new String[] { "customer"});
        ConstraintMapping custMapping = new ConstraintMapping();
        custMapping.setConstraint(custConstraint);          
        custMapping.setMethod("GET");
        custMapping.setPathSpec("/add");
        
		 

        ConstraintSecurityHandler handler = new ConstraintSecurityHandler();
        handler.addConstraintMapping(userMapping);
       handler.addConstraintMapping(custMapping);
       
        handler.setAuthenticator(new BasicAuthenticator());
		/*
		 * An Authenticator is responsible for checking requests and sending response
		 * challenges in order to authenticate a request. Various types of
		 * Authentication are returned in order to signal the next step in
		 * authentication.
		 */
        handler.setLoginService(new HashLoginService("RiderAutoParts", "src/main/resources/users.properties"));

        return handler;
    }

}
