package com.app;

 
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.*;

import org.apache.camel.component.jetty.JettyHttpComponent;
public class OrderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	System.out.println("OrderRoute config");
    	
    	 
        // configure rest-dsl
        restConfiguration()
           // to use jetty component and run on port 8080
            .component("jetty").port(8080)            
            // use a smaller thread pool in jetty as we do not have so high demand yet
            .componentProperty("minThreads", "1")
            .componentProperty("maxThreads", "8")
            .componentProperty("matchOnUriPrefix", "true")
            
             
            // to setup jetty to use the security handler
            .endpointProperty("handlers", "#securityHandler");
           // .endpointProperty("handlers",JettySecurity.createSecurityHandler());
       
      
		
		  JettyHttpComponent jettyComponent = getContext().getComponent("jetty",
		  JettyHttpComponent.class);
		  System.out.println("jettyComponent"+jettyComponent.getDefaultName());
		  
			//https://stackoverflow.com/questions/54629343/how-to-handle-errors-after-sending-requestcamel-http
			  
			  
			
			/*
			 * from("direct:input").setHeader(Exchange.HTTP_METHOD, constant("POST"))
			 * .to("http://localhost:8080/orders/get/1")
			 * .onException(org.apache.camel.http.common.HttpOperationFailedException.class)
			 * // .maximumRedeliveries(3)
			 * //.onException(java.lang.NumberFormatException.class).process(new
			 * ProcessFailureHandler()).stop() .handled(true)
			 * .transform(simple("The OPeration Failed")).log("${body}");
			 * 
			 * 
			 */
		   
        // rest services under the orders context-path
        rest("/orders")
            .get("/get/all")
                .to("bean:orderService?method=getAllOrders")                 
            .get("/get/{id}")
                .to("bean:orderService?method=getOrder(${header.id})")
            .get("/get")
                .to("bean:orderService?method=getSingleOrder()")    
            .post("/add")
                .to("bean:orderService?method=getSingleOrder()")
            .put("update/{id}")
                .to("bean:orderService?method=updateOrder(${header.id})")
            .delete("/remove/{id}")
                .to("bean:orderService?method=cancelOrder(${header.id})");
        
		/*
		 * from("direct:input").setHeader(Exchange.HTTP_METHOD, constant("POST"))
		 * .to("http://localhost:8080/orders/get/1")
		 * .onException(org.apache.camel.http.common.HttpOperationFailedException.class)
		 * // .maximumRedeliveries(3)
		 * //.onException(java.lang.NumberFormatException.class).process(new
		 * ProcessFailureHandler()).stop() .handled(true)
		 * .transform(simple("The OPeration Failed")).log("${body}");
		 */
		 
			 
    }
}
