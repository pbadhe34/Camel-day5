package com.app;

import java.util.Random;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // use http4 with rest dsl producer
        restConfiguration().producerComponent("http4")
            // to call rest service on localhost:8080 (the REST service from GeoRestController)
            .host("localhost").port(8080);
         // trigger the route every 5th second
        from("timer:foo?period=5000")            
            .to("rest:get:camel-rest-web/rest/provider/")           
            .transform().simple("${body}")
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
            return "Copenhagen";
        }
    }
}
