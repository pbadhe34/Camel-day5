package com.app;

import java.util.Random;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.dataformat.JsonLibrary;


@Component
public class RestProducerRoute extends RouteBuilder {

	@Override
    public void configure() throws Exception {
    	System.out.println("RestProducerRoute.configure()");
        // use http4 with rest dsl producer
		/*
		 * restConfiguration().producerComponent("http4") 
		 * // to call rest service on
		 * localhost:8080 (the REST service from GeoRestController)
		 * .host("localhost").port(8080); 
		 * // trigger the route every 5th second
		 * from("timer:foo?period=5000") 
		 * .to("rest:get:camel-rest-web/rest/provider/")
		 * .transform().simple("${body}") // print the response .log("${body}");
		 */
    	restConfiguration().producerComponent("http4")
        // to call rest service on localhost:8080 (the REST service from GeoRestController)
        .host("localhost").port(8080);

    // trigger the route every 5th second
       /*from("timer:foo?period=4000")
        // set a random city to use
        .setHeader("city", RestProducerRoute::randomCity)
        // use the rest producer to call the rest service
        .to("rest:get:country/{city}")
        // transform the response to grab a nice human readable
        .transform().jsonpath("$.results[0].formattedAddress")
        // print the response
        .log("${body}");*/
    	
        //Wrapper service for http://localhost:8090/REST-Service-Customer/customers/1
    	from("timer:foo?period=4000")
        // set a random city to use
        .setHeader("id", RestProducerRoute::randomID)
        
    	.to("rest:get:customerService/{id}")
        // transform the response to grab a nice human readable
       // .transform().jsonpath("$.mailingAddress")
    	.unmarshal().json(JsonLibrary.Jackson, Customer.class) 
    	.transform().simple("Extracted Values : ${body.firstName} + ${body.lastName} + ${body.id}")
        
        // print the response
        .log("${body}");
}

	/**
	 * Generate a random city
	 */
	public static Object randomCity() {
		int ran = new Random().nextInt(3);
		if (ran == 0) {
			return "London";
		} else if (ran == 1) {
			return "Paris";
		} else {
			return "Mumbai";
		}
	}
	
	/**
	 * Generate a random city
	 */
	public static Object randomID() {
		int ran = new Random().nextInt(2);
		if (ran == 0) {
			return 1;
		} else if (ran == 2) {
			return 2;
		} else {
			return 1;
		}
	}
}
