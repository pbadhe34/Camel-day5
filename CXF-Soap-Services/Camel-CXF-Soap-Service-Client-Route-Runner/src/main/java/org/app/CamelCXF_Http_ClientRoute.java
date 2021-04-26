
package org.app;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
 
/**
 * The Camel route
 */

public class CamelCXF_Http_ClientRoute extends RouteBuilder {

	 
	//CXF Soap service endpoint url
	private String uri = "http://localhost:8090/camel-soap-cxf-app/webservices/data";
	
	 
	@Override
	public void configure() throws Exception {
		System.out.println("CamelCXF_Http_ClientRoute -->:configure");
		//Monitor the output of CXF service
		from("direct:client")
		.setHeader(Exchange.HTTP_METHOD, constant("POST")) 
		 		 
		.process(new Processor(){ 		 
			
			@Override
			public void process(Exchange exchange) throws Exception {
				//exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
				//exchange.getIn().setHeader(name, value);
				String inputType = exchange.getIn().getBody().getClass().getName();
				String inData = exchange.getIn().getBody(String.class);
				
				System.out.println("CamelCXF_ClientRoute Direct Input Data -->:"+inData);
				System.out.println("CamelCXF_ClientRoute Direct Input type -->:"+inputType);
				System.out.println("CamelCXF_ClientRoute Direct Input contents -->:"+exchange.getIn().getBody(String.class));
				//Neeed to set the output, defulat it is null
				//Object outputType = exchange.getOut().getBody();
				//System.out.println("CamelCXF_ClientRoute Direct Output type -->:"+outputType);
				
				exchange.getOut().setBody(inData);			
				
				  
			}
		})
		.to(uri).process(new Processor(){ 		 
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String inputType = exchange.getIn().getBody().getClass().getName();
				System.out.println("CamelCXF_ClientRoute CXF URL Process Out type -->:"+inputType);
				
				String inData = exchange.getIn().getBody(String.class);
				
				//Neeed to set the output, defulat it is null
				//String outputType = exchange.getOut().getBody().getClass().getName();
				System.out.println("CamelCXF_ClientRoute CXF ProcessOut Data -->:"+inData);
			//	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns1:statusIncidentResponse xmlns:ns1="http://server.com/"><ns2:return xmlns:ns2="http://server.com/"><status>IN PROGRESS Now</status></ns2:return></ns1:statusIncidentResponse></soap:Body></soap:Envelope>
				if(inData.contains("status"))
			   {
					String value = "<status>";
					int sizeStatusString = value.length();
					int start = inData.indexOf("<status>");
					int end = inData.indexOf("</status>");
					System.out.println("CamelCXF_ClientRoute Index of start -->:"+start);
					System.out.println("CamelCXF_ClientRoute Index of start -->:"+end);
					 //<status>IN PROGRESS Now
					String out = inData.substring(start+sizeStatusString, end);
					
					exchange.getOut().setBody("The out response is : "+out);	
				}
				else
					exchange.getOut().setBody(inData);				 
						 
						 
			}
		})
		.to("stream:out")
		.log("The output is ${body}");
		
		 
	}

		 
}
