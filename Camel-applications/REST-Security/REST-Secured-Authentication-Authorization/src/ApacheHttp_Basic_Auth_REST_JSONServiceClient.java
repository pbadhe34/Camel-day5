 

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.rest.User;

 

public class ApacheHttp_Basic_Auth_REST_JSONServiceClient
{
	public static void main(String[] args) throws Exception 
	{
		//Demo Get request
		 Test_GetRESTAPI();
		
		//Demo Post request
		//Test_PostRESTAPI();
	}
	
	
	public static void Test_GetRESTAPI() throws Exception 
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try
		{
			//Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
			//Choice depends on type of method you will be invoking.
			HttpGet getRequest = new HttpGet("http://localhost:8080/REST-Secured-Authentication-Authorization/product/get");
			
			//Set the API media type in http accept header
			getRequest.addHeader("accept", "application/json");
			
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope("localhost", 8080),
					new UsernamePasswordCredentials("admin", "admin"));

			 
			//Send the request; It will immediately return the response in HttpResponse object
			HttpResponse response = httpClient.execute(getRequest);
			
			//verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) 
			{
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			
			//Now pull back the response object
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);
			
			//The result of get call to xml rest service
			System.out.println("\nTHe response from get xml is \n");
			System.out.println(apiOutput); 
			
			//In realtime programming, you will need to convert this http response to some java object to re-use it.
			//Lets see how to jaxb unmarshal the api response content
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	        
	        //Verify the java converted object
	        System.out.println("\nThe xml to java converted object is  \n");
	        System.out.println(user.getId());
	        System.out.println(user.getUsername());
	        System.out.println(user.getPassword());
	        System.out.println(user.getPin());
	        System.out.println();
		}
		finally
		{
			//Important: Close the connect
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	public static void Test_PostRESTAPI() throws Exception 
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		User user = new User();
		user.setId(100);
		user.setUsername("Mayur");
		user.setPassword("secret");
		user.setPin(56789);
		
		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(user, writer);
        
        System.out.println("\nThe xml converted object to be posted is  "+writer);
        System.out.println();
        
		try
		{
			//Define a postRequest request
			HttpPost postRequest = new HttpPost("http://localhost:8080/REST-XML-JAXB-Service/xml/user/post");
			
			//Set the API media type in http content-type header
			postRequest.addHeader("content-type", "application/xml");
			
			//Set the request post body
			StringEntity userEntity = new StringEntity(writer.getBuffer().toString());
			postRequest.setEntity(userEntity);
			 
			//Send the request; It will immediately return the response in HttpResponse object if any
			HttpResponse response = httpClient.execute(postRequest);
			
			//verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 201) 
			{
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			System.out.println("\nThe statusCode response to post xml method "+statusCode);
		}
		finally
		{
			//Important: Close the connect
			httpClient.getConnectionManager().shutdown();
		}
	}
}
