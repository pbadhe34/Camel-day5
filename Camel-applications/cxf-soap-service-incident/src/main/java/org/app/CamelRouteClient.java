 
package org.app;

import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.InputStatusIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;

 
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
 
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class CamelRouteClient {

	 
    private static final String URL = "http://localhost:8090/"
    		+ "camel-soap-cxf-app/webservices/incident";
    
    protected static IncidentService createCXFClient() {
        // we use CXF to create a client for us as 
    	//its easier than JAXWS and works
    	System.out.println("The CXF Service in createCXFClient");
         
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(IncidentService.class);
        factory.setAddress(URL);
        //return (IncidentService) factory.create();
        
        //IncidentService service = (IncidentService) factory.create();
        IncidentService service = new IncidentService() {

			public OutputReportIncident reportIncident(InputReportIncident input) {
				System.out.println("\n**reportIncident on Service Impl***\n");
				System.out.println("\n********************\n");
				System.out.println("\n**reportIncident with :"+input.getIncidentId());
				System.out.println("\n********************\n");
				OutputReportIncident obj = new OutputReportIncident();
				obj.setCode("From Service impl code on camel-service");
				return obj;
			}

			public OutputStatusIncident statusIncident(InputStatusIncident input) {
				System.out.println("\n**reportIncident on Service Impl***\n");
				System.out.println("\n#############*\n");
				System.out.println("\n******statusIncident******\n");
				System.out.println("\n******statusIncident data ******: "+input.getIncidentId());
				OutputStatusIncident os = new OutputStatusIncident();
				os.setStatus("From Camel Service");
				return os;
			}
        	
        };
         
         return service;
    }

    public static void main(String[] args) throws Exception {
        CamelRouteClient client = new CamelRouteClient();
        System.out.println("The Camel CXF Service Client is  running");
        
        
        //runRoutes();
        //Thread.sleep(1000);
       // client.runTest();
        
        
        
    }
    public static void runRoutes()throws Exception {
		CamelContext ctx = new DefaultCamelContext();

		CamelRoute router = new CamelRoute();
		ctx.addRoutes(router);

		 System.out.println("The starting of  CamelContext");
		ctx.start();
		ProducerTemplate templ = ctx.createProducerTemplate();
		//templ.sendBody("direct:getData", 2);
         System.out.println("The Routes are running");
		 Thread.sleep(150000);
		 ctx.stop();
		 ctx.shutdown();

	}

    
    protected void runTest() throws Exception {
       
        // create input parameter
        InputReportIncident input = new InputReportIncident();
        input.setIncidentId("123");
        input.setIncidentDate("2008-08-18");
        input.setGivenName("Claus");
        input.setFamilyName("Ibsen");
        input.setSummary("Bla");
        input.setDetails("Bla bla");
        input.setEmail("davsclaus@apache.org");
        input.setPhone("0045 2962 7576");

        // create the webservice client and send the request
        System.out.println("The CXF Service is before  createCXFClient");
        
        IncidentService client = createCXFClient();
        System.out.println("The CXF Service is after the   createCXFClient");
        
        OutputReportIncident out = client.reportIncident(input);
        System.out.println(out.getCode());
        InputStatusIncident inStatus = new InputStatusIncident();
        inStatus.setIncidentId("456");
        OutputStatusIncident outStatus = client.statusIncident(inStatus);
        System.out.println(outStatus.getStatus());
        System.out.println(outStatus.getStatus());
       
    }

}
