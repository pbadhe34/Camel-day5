import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class REST_JSON_Client_Get {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		  System.out.println("*** GET Customer as JSON **");
		  URL getUrl = new URL("http://localhost:8080/REST-Service/customers/");
	      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	      
	      
	      // Get json structure of customer object
	     
	      connection = (HttpURLConnection) getUrl.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Accept", "application/json");
	      //HttpURLConnection.HTTP_OK, connection.getResponseCode());
	    
	      System.out.println("The response code is  "+connection.getResponseCode());
	       
	      System.out.println("The response content type is "+connection.getContentType());
	      
	      //"application/json", connection.getContentType());

		   
	      BufferedReader reader = new BufferedReader(new
	              InputStreamReader(connection.getInputStream()));
	      System.out.println("\nThe JSON format of customer sent by server...\n");

	      
	      String line = reader.readLine();
	      while (line != null)
	      {
	         System.out.println(line);
	         line = reader.readLine();
	      }
	      connection.disconnect();
	      
	      // Get structure of customer object as plain text
	      /*System.out.println("\n*** GET Customer as plain text **\n");
	      connection = (HttpURLConnection) getUrl.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Accept", "text/plain");
	      System.out.println("The response code is  "+connection.getResponseCode());
	       
	      System.out.println("The response content type is "+connection.getContentType());
	      
	     
	      reader = new BufferedReader(new
	              InputStreamReader(connection.getInputStream()));

	      line = reader.readLine();
	      while (line != null)
	      {
	         System.out.println(line);
	         line = reader.readLine();
	      }
	      connection.disconnect();
*/



	}

}
