package com.app;

import com.app.dummy.DummyOrderService;
import org.apache.camel.main.Main;

/**
 * A main class to try to run this example
 */
public class OrderMainApp {

    public static void main(String[] args) throws Exception {
        //Camel Main class
    	Main main = new Main();

        // setup a dummy order service
        main.bind("orderService", new DummyOrderService());
        
        // add the order route with the Rest services
       // main.addRouteBuilder(new OrderRoute());
        main.configure().addRoutesBuilder(new OrderRoute());
        
     
        // create Jetty Basic Auth security handler
         main.bind("securityHandler", JettySecurity.createSecurityHandler());
        // main.bind("handlers", JettySecurity.createSecurityHandler());

        

        System.out.println("***************************************************");
        System.out.println("");
        System.out.println("  Rider Auto Parts REST order service");
        System.out.println("");
        System.out.println("  You can try calling this service using http://localhost:8080/orders/get/1");
        System.out.println("");
        System.out.println("    and use jack/123 as username/password");
        System.out.println("***************************************************");

        // run the application
        main.run();

    }
}
