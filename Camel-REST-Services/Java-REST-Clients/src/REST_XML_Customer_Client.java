import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;


public class REST_XML_Customer_Client {

	 
	public static void main(String[] args) throws IOException {
		 System.out.println("*** GET Customer as XML **");
	      URL getUrl = new URL("http://localhost:8080/REST_XML_Service/customers/1");
	      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Accept", "application/xml");
	      System.out.println("The response code is  "+connection.getResponseCode());
	      
	      
	      System.out.println("The response content type is "+connection.getContentType());
		     

	      BufferedReader reader = new BufferedReader(new
	              InputStreamReader(connection.getInputStream()));

	      String line = reader.readLine();
	      while (line != null)
	      {
	         System.out.println(line);
	         line = reader.readLine();
	      }
	      connection.disconnect();


		/*System.out.println("*** Create a new Customer xml and post to service***");
	      // Create a new customer
	      String newCustomer = "<customer>"
	              + "<first-name>Bill</first-name>"
	              + "<last-name>Clinton</last-name>"
	              + "<street>256 White House Street</street>"
	              + "<city>Washington</city>"
	              + "<state>WS</state>"
	              + "<zip>199</zip>"
	              + "<country>USA</country>"
	              + "</customer>";

	      URL postUrl = new URL("http://localhost:8080/REST_XML_Service/customers/");
	      connection = (HttpURLConnection) postUrl.openConnection();
	      connection.setDoOutput(true);
	      connection.setInstanceFollowRedirects(false);
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", "application/xml");
	      OutputStream os = connection.getOutputStream();
	      os.write(newCustomer.getBytes());
	      os.flush();
	      System.out.println("The http status respopnse code is  "+connection.getResponseCode());
	    
	      System.out.println("Location: " + connection.getHeaderField("Location"));
	      connection.disconnect();*/
	      
	      
	   // Update customer json object
			System.out.println("*** Update Customer as XML **");

			URL updateUrl = new URL("http://localhost:8080/REST_XML_Service/customers/2");

			HttpURLConnection conn2 = (HttpURLConnection) updateUrl.openConnection();
			conn2.setDoOutput(true);
			conn2.setRequestMethod("PUT");
			conn2.setRequestProperty("Content-Type", "application/xml");
			 
			 String customer = "<customer id='2'>"
		              + "<first-name>Hillary</first-name>"
		              + "<last-name>Rodham</last-name>"
		              + "<street>256 White House Street</street>"
		              + "<city>Washington</city>"
		              + "<state>WS</state>"
		              + "<zip>199</zip>"
		              + "<country>US</country>"
		              + "</customer>";
			 
			 
			//System.out.println("The oputput sent is  " + customer);

			OutputStream os1 = conn2.getOutputStream();
			os1.write(customer.getBytes());
			os1.flush();
			System.out.println("The http status respopnse in put code is  " + conn2.getResponseCode());

			System.out.println("The http response created code is  " + HttpURLConnection.HTTP_CREATED);

			BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));

			String output = null;
			System.out.println("Output from the Server .... \n");
			while ((output = br2.readLine()) != null) {
				System.out.println(output);
			}

			conn2.disconnect();

			// Delete customer XML object
			System.out.println("*** Delete Customer as XML **");
             // send requst to delete customer with id =1
			URL deleteUrl = new URL("http://localhost:8080/REST_XML_Service/customers/1");

			HttpURLConnection con = (HttpURLConnection) deleteUrl.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("DELETE");
			// con.setRequestProperty("Content-Type", "application/xml");
			// con.setInstanceFollowRedirects(false);

			System.out.println("The response code in delete opeartion is  " + con.getResponseCode());

			System.out.println("The response content type is " + con.getContentType());

			BufferedReader reader2 = new BufferedReader(new InputStreamReader(con.getInputStream()));

			line = null;
			line = reader2.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader2.readLine();
			}
			con.disconnect();


	}

}
