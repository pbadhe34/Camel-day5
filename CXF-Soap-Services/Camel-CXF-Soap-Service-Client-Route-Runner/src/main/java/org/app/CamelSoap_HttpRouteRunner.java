 
package org.app;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelSoap_HttpRouteRunner {

	 
   // private static final String url = "http://localhost:8090/camel-soap-cxf-app/webservices/data";
    
     
    public static void main(String[] args) throws Exception {         
        
      //Deploy the camel-route and run cxf jaxws client to test the action in camel route
        
        runRoutes();
        
        
        
    }
    public static void runRoutes()throws Exception {
		CamelContext ctx = new DefaultCamelContext();
		ctx.setTracing(true);
		 
		 

		CamelCXF_Http_ClientRoute router = new CamelCXF_Http_ClientRoute();
		ctx.addRoutes(router);

		 System.out.println("The starting of CamelContext");
		ctx.start();
		
		String reportIncidentXML = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>"
				+ "<ns2:reportIncident xmlns:ns2=\"http://server.com/\">"
				+ "<ns2:input><details>Details-519101020</details>"
				+ "<email>sid@server.net</email>"
				+ "<familyName>Baba</familyName>"
				+ "<givenName>Tushar</givenName>"
				+ "<incidentDate>Sat Apr 24 15:41:43 IST 2021</incidentDate>"
				+ "<incidentId>IncidentId-1349440148</incidentId>"
				+ "<phone>Phone-1326730007</phone>"
				+ "<summary>Summary1-Data</summary>"
				+ "</ns2:input>"
				+ "</ns2:reportIncident>"
				+ "</soap:Body></soap:Envelope>";
		
			 
		String StatusIncidentXML = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body>"
				+ "<ns2:statusIncident xmlns:ns2=\"http://server.com/\"><ns2:input>"
				+ "<incidentId>IncidentIdStatus-1621648532</incidentId>"
				+ "</ns2:input></ns2:statusIncident></soap:Body></soap:Envelope>";
		
		ProducerTemplate templ = ctx.createProducerTemplate();
	//	templ.sendBody("direct:client", reportIncidentXML); //opertaion name is NOT identified on server router
		templ.sendBodyAndHeader("direct:client", reportIncidentXML, "operationName", "reportIncident");
         System.out.println("The Routes are running");
         Thread.sleep(2000);
		 //templ.sendBody("direct:client", reportIncidentXML);/opertaion name is NOT identified on server router
         templ.sendBodyAndHeader("direct:client", StatusIncidentXML, "operationName", "statusIncident");
		 Thread.sleep(250000);
		 ctx.stop();
		 ctx.shutdown();
	} 
}
    
