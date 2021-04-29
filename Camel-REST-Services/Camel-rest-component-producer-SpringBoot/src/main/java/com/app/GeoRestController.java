package com.app;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
public class GeoRestController {

    @EndpointInject(uri = "log:foo")  
    private FluentProducerTemplate template;
    
    //https://developers.google.com/maps/gmp-get-started#enable-api-sdk
    //https://console.cloud.google.com/google/maps-apis/api-list?project=app-gmap
    
	/*
	 * @RequestMapping("/country/{city}") public Object address(@PathVariable(name =
	 * "city") String city) {
	 */      // use Camel's geocoder to get location about the city
       // return template.to("geocoder:address:" + city).request();
          //The respose of http://localhost:8080/country/Mumbai is 
          //{"status":"REQUEST_DENIED","results":[]}
          //Need to set the Google Maps access with your own secured API Keys
        
		/*
		 * To use Google Maps Platform, you must enable the APIs (The GeoCoding API)or SDKs you plan to use
		 * with your project on Cloud Console.
		 */
    	
    @RequestMapping("/customerService/{id}")
    public Object getData(@PathVariable(name = "id") Integer id) {
    
    	  	/* Return Provider objhect as json
			 * Provider obj = new Provider(); obj.setId(id); obj.setFirstName("Baba");
			 * obj.setLastName("Bhosale"); obj.setMailingAddress("jaav@dfal.net");
			 * obj.setMobilePhone("85-4321098"); obj.setZipCode("435213"); return obj;
			 */
    	//Call another endpoint or service --(in this case) and return the response
    	FluentProducerTemplate templ = template.to("http://localhost:8090/REST-Service-Customer/customers/" + id);
    	//templ.request();
    	return templ.request(String.class);
		
    }
}
