package com.server;

import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.InputStatusIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;

public class IncidentServiceImpl implements IncidentService{
	
	 

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
			os.setStatus("From Camel Service Impl");
			return os;
		}	
 

}
