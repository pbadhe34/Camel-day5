package com.app;


import org.apache.camel.main.Main;
import org.apache.camel.model.rest.RestBindingMode;
 

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

 
public class AppRouteRunner {

    public static void main(String[] args) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
    	RestProducerRoute router = new RestProducerRoute();
        ctx.addRoutes(router);       
		ctx.start();     
        System.out.println("\n*****The camel Context started in AppRouteRunner");    
        
         Thread.sleep(2303000);         
         ctx.stop();        
        
    }

}
