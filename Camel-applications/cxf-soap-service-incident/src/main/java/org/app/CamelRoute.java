 
package org.app;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;

 
/**
 * The Camel route
 */
 
public class CamelRoute extends RouteBuilder {

    // CXF webservice using code first approach
    private String uri = "cxf:/incident?serviceClass=" + com.server.IncidentServiceImpl.class.getName();

    @Override
    public void configure() throws Exception {
    	System.out.println("CamelRoute -->:configure");
        from(uri)
            .to("log:input")            
            // send the request to the route to handle the operation
            // the name of the operation is in that header
            .recipientList(simple("direct:${header.operationName}"));

        // report incident
        from("direct:reportIncident")        
             .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    // get the id of the input
                	System.out.println("CamelRoute -->:reportIncident");
                	// get the id of the input
                    String id = exchange.getIn().getBody(InputReportIncident.class).getIncidentId();

                    // set reply including the id
                    OutputReportIncident output = new OutputReportIncident();
                    output.setCode("OK Now;" + id);
                    exchange.getOut().setBody(output);
                }
            })
            .to("log:output");

        // status incident
        from("direct:statusIncident")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    // set reply
                	System.out.println("CamelRoute -->:statusIncident");
                    
                	// set reply
                    OutputStatusIncident output = new OutputStatusIncident();
                    output.setStatus("IN PROGRESS Now");
                    exchange.getOut().setBody(output);
                }
            })
            .to("log:output");
    }
}
 
