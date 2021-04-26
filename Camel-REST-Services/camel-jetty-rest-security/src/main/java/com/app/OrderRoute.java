package com.app;

 
import org.apache.camel.builder.RouteBuilder;

import java.util.Properties;

import org.apache.camel.*;

import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
public class OrderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	System.out.println("OrderRoute config");
    	
    	 CamelContext ctx = getContext();
    	  
    	 
    	 Properties properties = new Properties();
    	    properties.setProperty("CamelJacksonEnableTypeConverter", "true");
    	 // allow Jackson json to convert to pojo types also
    	    properties.setProperty("CamelJacksonTypeConverterToPojo", "true");
    	    
    	 
    	    PropertiesComponent pc  = new PropertiesComponent();
    	    pc.setInitialProperties(properties);
    	    ctx.setPropertiesComponent(pc);
         
        
       // RuntimeSupport.configureContextCustomizers(context);
         


        
        // configure rest-dsl to generate rest api with http server
        restConfiguration()
           // to use jetty component and run on port 8080
            .component("jetty").port(8080)            
            // use a smaller thread pool in jetty as we do not have so high demand yet
            .componentProperty("minThreads", "1")
            .componentProperty("maxThreads", "8")
            .componentProperty("matchOnUriPrefix", "true")
            .bindingMode(RestBindingMode.json)
            
             
            // to setup jetty to use the security handler
            .endpointProperty("handlers", "#securityHandler");
           //.endpointProperty("handlers",JettySecurity.createSecurityHandler());
       
      
		
		  JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
		  System.out.println("jettyComponent"+jettyComponent.getDefaultName());
		  
		  //http://localhost:8080/orders/get/1
		  //  http://localhost:8080/orders/all/add/456
			  
	     // http://localhost:8080/orders/all/add/3 	  
		  
			 
			 
		  //http://localhost:8080/orders/get/1
		   
        // rest services implementation under the orders context-path
		 
		//  RestConfigurationDefinition RestDefinition RouteDefinition
		  CustomProcessor pocessor = new CustomProcessor();
		  
			/*
			 * rest("/test") .get("/data") .to("file:src/data/data.json");
			 * 
			 */
		  
		   
          
               
              
              
		  
		      rest("/orders")            
		            .get("/all/orders")
		                .to("bean:orderService?method=getAllOrders")  
		            .get("/get/{id}")
		                .to("bean:orderService?method=getOrder(${header.id})")
		            .get("/get")
		                .to("bean:orderService?method=getSingleOrder()")    
		            .get("/getSingle")
		                .to("bean:orderService?method=getSingleOrder()")
		            .put("update/{id}")
		                .to("bean:orderService?method=updateOrder(${header.id})")
		             .post("/add")
		                .to("bean:orderService?method=createOrder(${header.body})")
		            .delete("/remove/{id}")
		                .to("bean:orderService?method=cancelOrder(${header.id})")
		            .get("/all/add/{id}").route().process(pocessor)
		            .to("bean:orderService?method=handlePostMethod(${header.id})")		  
		               .onException(NumberFormatException.class)
		               .handled(true).setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
					    //Redircet the browser in case of errors in rest service
					     // .setHeader(Exchange.HTTP_URL, constant("https://www.google.com/search?q=http%20method"));
					    .transform().constant("Invalid Input Data for this URL")	 
				  		
					    .onException(CustomException.class)
				  		.handled(true)
				  		.transform().simple("THe data ${body} is not correct");
				  		
				 	   	//http://localhost:8080/orders/all/add/1		         
		  
		     
			/*
			 * RestDefinition rstd = rest("/orders") .get("/all/orders")
			 * .to("bean:orderService?method=getAllOrders") .get("/get/{id}")
			 * .to("bean:orderService?method=getOrder(${header.id})") .get("/get")
			 * .to("bean:orderService?method=getSingleOrder()") .post("/add")
			 * .to("bean:orderService?method=getSingleOrder()") .put("update/{id}")
			 * .to("bean:orderService?method=updateOrder(${header.id})")
			 * .delete("/remove/{id}")
			 * .to("bean:orderService?method=cancelOrder(${header.id})");
			 * 
			 * 
			 * //Define one more rest route RouteDefinition route7
			 * =rstd.get("/all/add/{id}").route().process(pocessor)
			 * .to("bean:orderService?method=handlePostMethod(${header.id})");
			 * 
			 * 
			 * //Exception hnadler for this route route7.onException(CustomException.class).
			 * handled(true).setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
			 * //Redircet the browser in case of errors in rest service //
			 * .setHeader(Exchange.HTTP_URL,
			 * constant("https://www.google.com/search?q=http%20method"));
			 * .transform().constant("Invalid Input Data");
			 */
		 
			 
    }
}
