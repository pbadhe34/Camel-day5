import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class REST_XML_Client_Post {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("*** Create a new Customer and post it***");
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

	      URL postUrl = new URL("http://localhost:8080/REST_XML_JSON/customers");
	      HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
	      connection.setDoOutput(true);
	      connection.setInstanceFollowRedirects(false);
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", "application/xml");
	      OutputStream os = connection.getOutputStream();
	      os.write(newCustomer.getBytes());
	      os.flush();
	      System.out.println("The http status respopnse code is  "+connection.getResponseCode());
	      //HttpURLConnection.HTTP_CREATED 
	      System.out.println("Location: " + connection.getHeaderField("Location"));
	      connection.disconnect();



	}

}
