 
package org.app;

 
import com.server.Incident;
import com.server.IncidentPortType;
import com.server.InputReportIncident;
import com.server.InputStatusIncident;
import com.server.OutputReportIncident;
import com.server.OutputStatusIncident;

 
 
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxb.JAXBDataBinding;

public class CXF_Soap_Proxy_Client {

	 
	//This dynamic client doesnot pass the input objcet type 
    private static final String url = "http://localhost:8090/camel-soap-cxf-app/webservices/data?wsdl";
    
    protected static IncidentPortType createCXFClient() {
        // we use CXF to create a client for us as 
    	//its easier than JAXWS and works
    	System.out.println("The CXF Service Client in createCXFClient");
         
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(IncidentPortType.class);
        factory.setAddress(url);
        //set the data binding for java to xml and reverse conversions
       // factory.setDataBinding(new JAXBDataBinding());
         
        return (IncidentPortType) factory.create();          
        
		 
    }

    public static void main(String[] args) throws Exception {
    	CXF_Soap_Proxy_Client client = new CXF_Soap_Proxy_Client();
        System.out.println("The CXF_Soap_Proxy_Client is  running");        
      
       
        client.runTest();
        
        System.out.println("The Camel CXF Service Client stopped");
        
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
        
        IncidentPortType client = createCXFClient();
        System.out.println("The CXF Service is after the   createCXFClient "+client);
        
		
		  OutputReportIncident out = client.reportIncident(input);
		  System.out.println(out.getCode());
		 
		 
        InputStatusIncident inStatus = new InputStatusIncident();
        inStatus.setIncidentId("456");
        OutputStatusIncident outStatus = client.statusIncident(inStatus);
        System.out.println(outStatus.getStatus());
         
       
    }

}
