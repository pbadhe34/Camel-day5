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

    @RequestMapping("/country/{city}")
    public Object address(@PathVariable(name = "city") String city) {
        // use Camel's geocoder to get location about the city
        return template.to("geocoder:address:" + city).request();
    }
}
