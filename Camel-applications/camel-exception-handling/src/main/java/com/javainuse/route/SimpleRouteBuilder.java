package com.javainuse.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import com.javainuse.exception.CamelCustomException;
import com.javainuse.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelCustomException.class).process(new Processor() {

            public void process(Exchange exchange) throws Exception {
                System.out.println("handling exception globally");
            }
        }).log("Received body ${body}").handled(true);

		
		  from("file:C:/inputFolder?noop=true").process(new
		  MyProcessor()).to("file:C:/outputFolder");
		  
		  from("file:C:/inbox?noop=true").process(new
		  MyProcessor()).to("file:C:/outputFolder");
		 
        from("file:C:/input?noop=true").to("log:test")
        .onException(RuntimeException.class).process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				 
				System.out.println("handling exception locally with file processor");
				throw new CamelCustomException();
			}
        	
        });
		/*
		 * from("direct:input").process(new Processor() {
		 * 
		 * public void process(Exchange exchange) throws Exception {
		 * 
		 * System.out.println("Throwing the exception locally in direct mode"); throw
		 * new RuntimeException(); }
		 * 
		 * }).onException(RuntimeException.class).process(new Processor() {
		 * 
		 * public void process(Exchange exchange) throws Exception {
		 * 
		 * System.out.println("handling exception locally with file processor"); //throw
		 * new CamelCustomException(); }
		 * 
		 * }).to("file:C:/input");
		 */
        
        from("direct:input").process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				
				System.out.println("Throwing the exception locally in direct mode");
				//throw new RuntimeException();
			}
        	
        }).to("file:C:/input").onException(RuntimeException.class).process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				 
				System.out.println("handling exception locally with file processor");
				throw new CamelCustomException();
			}
        	
        });
        
        
    }

}
