
package org.app;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.message.MessageContentsList;
import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.InputStatusIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;

/**
 * The Camel route
 */

public class CamelRoute extends RouteBuilder {

	// CXF webservice using code first approach and deployed on Tomcat with impl
	// from this camel project
	// The end point of the service generated is
	// http://localhost:8090/camel-soap-cxf-app/webservices/data
	// with service name as Incident and port name as IncidentPortType and
	// portBinding as IncidentPort
	// The wsdl url is
	// http://localhost:8090/camel-soap-cxf-app/webservices/data?wsdl
	// with interface and service class as Incident.class as specified in next line
	
	
	/*
	 * The CXF-RS componnet exposes JAX-RS REST services implemented with Apache CXF
	 * in camel server //OR connect as client to external REST services using CXF
	 * REST client.
	 */
	
	//The camel-CXF exposes SOAP WebServices implemented with Apache CXF in camel
	//OR  as a client connects to external WebServices using CXF WS client.
	
	
	private String uri = "cxf:/data?serviceClass=" + com.server.Incident.class.getName();

	@Override
	public void configure() throws Exception {
		System.out.println("CamelRoute -->:configure");
		from(uri).to("log:input")
				// send the request to the route to handle the operation
				// the name of the operation is in that header
				.recipientList(simple("direct:${header.operationName}"));

		// report incident
		from("direct:reportIncident").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				// get the id of the input
				System.out.println("\n***CamelRoute on server-->:reportIncident");

				String inClass = exchange.getIn().getBody().getClass().getName();
				System.out.println("CamelCXF_ClientRoute reportIncident input body type -->:" + inClass);
				// org.apache.cxf.message.MessageContentsList
				MessageContentsList iList = (MessageContentsList) exchange.getIn().getBody();
				System.out
						.println("CamelCXF_ClientRoute reportIncident input body mesages size is  -->:" + iList.size());

				 
				// get the id of the input

				
				//Problem with CXF Dynamic Clinets creatd by ClientProxyFactoryBean
				String id = exchange.getIn().getBody(InputReportIncident.class).getIncidentId();			 
				 

				System.out.println("\n***CamelRoute -->:reportIncident InputReportIncident id retrieved  "+id);

				// get the id of the input

				/*
				 * String inData = exchange.getIn().getBody(String.class);
				 * System.out.println("CamelCXF_ClientRoute reportIncident input body -->:"
				 * +inData); String value = "<email>"; int sizeStatusString = value.length();
				 * int start = inData.indexOf("<email>"); int end = inData.indexOf("</email>");
				 * System.out.println("CamelCXF_ServiceRoute Index of email start -->:"+start);
				 * System.out.println("CamelCXF_ClientRoute Index of email end -->:"+end);
				 * 
				 * String emailValue = inData.substring(start+sizeStatusString, end);
				 */

			
				// set reply including the id
				OutputReportIncident output = new OutputReportIncident();
				output.setCode("OK From Server for ID : "+id);
				exchange.getOut().setBody(output);
			}
		}).to("log:output");

		// status incident
		from("direct:statusIncident").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				// set reply
				System.out.println("\n$$$$CamelRoute  on server-->:statusIncident");
				/*
				 * String inClass = exchange.getIn().getBody().getClass().getName();
				 * System.out.println("CamelCXF_ClientRoute statusIncident input body type -->:"
				 * + inClass);
				 * 
				 * String inData = exchange.getIn().getBody(String.class);
				 * System.out.println("CamelCXF_ClientRoute statusIncident input body -->:" +
				 * inData); String value = "<incidentId>"; int sizeStatusString =
				 * value.length(); int start = inData.indexOf("<incidentId>"); int end =
				 * inData.indexOf("</incidentId>");
				 * System.out.println("CamelCXF_ServiceRoute Index of incidentId start -->:" +
				 * start);
				 * System.out.println("CamelCXF_ClientRoute Index of incidentId end -->:" +
				 * end);
				 * 
				 * String incidentIdValue = inData.substring(start + sizeStatusString, end);
				 * 
				 * // set reply including the sttaus with id
				 */
				//	//Problem with CXF Dynamic Clinets creatd by ClientProxyFactoryBean
				InputStatusIncident indata = exchange.getIn().getBody(InputStatusIncident.class);
				OutputStatusIncident output = new OutputStatusIncident();
				output.setStatus("IN PROGRESS ON Server for ID : " + indata.getIncidentId());
				exchange.getOut().setBody(output);
			}
		}).to("log:output");
	}
}
