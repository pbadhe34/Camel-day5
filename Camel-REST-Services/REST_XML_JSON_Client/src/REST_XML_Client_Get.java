import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class REST_XML_Client_Get {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		  System.out.println("*** GET Customer as XML **");
	      URL getUrl = new URL("http://localhost:8080/REST_XML_JSON/customers/1");
	      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Accept", "application/xml");
	      System.out.println("The response code is  "+connection.getResponseCode());
	      //HttpURLConnection.HTTP_OK, );
	      
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




	}

}
