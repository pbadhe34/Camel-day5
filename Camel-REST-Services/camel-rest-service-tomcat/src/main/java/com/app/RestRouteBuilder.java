 
package com.app;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

 
public class RestRouteBuilder extends RouteBuilder {
	
//	/http://localhost:8080/config/config/swagger.json
	

/*
 * <servlet-mapping> <servlet-name>default</servlet-name>
 * <url-pattern>/</url-pattern> </servlet-mapping>
 * 
 * 
 * <servlet> <servlet-name>default</servlet-name>
 * <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
 * <load-on-startup>1</load-on-startup> </servlet>
 */
 

    @Override
    public void configure() throws Exception {

        // configure we want to use servlet as the component for the rest DSL
        // and we enable json binding mode
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
            // and output using pretty print
            .dataFormatProperty("prettyPrint", "true")
             
            .contextPath("camel-rest-web/rest").port(8080);

        // this provider REST service is json only
        rest("/provider").description("Provider rest service")
            .consumes("application/json").produces("application/json")

            .get("/{id}").description("Find provider by id").outType(Provider.class)
                .to("bean:providerService?method=getProvider(${header.id})")

            .put().description("Updates or create a provider").type(Provider.class)
                .to("bean:providerService?method=updateProvider")              
               
				
				  .get().description("List all providers").outType(Provider[].class)
				  .to("bean:providerService?method=listProviders")
				 
                
        	.get("/search").description("Search by Zip").outType(Provider[].class)
        		.route().log("Incoming zip: ${header.zip}")
        		.to("bean:providerService?method=searchByZip(${header.zip})").endRest();
    }

}