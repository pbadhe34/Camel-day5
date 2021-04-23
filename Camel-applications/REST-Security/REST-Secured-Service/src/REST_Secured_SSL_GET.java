import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection; 
import java.net.URL;
 

public class REST_Secured_SSL_GET {

	public static void main(String[] args) throws IOException {
		System.out.println("*** GET SSL data as JSON **");
	 
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			    new javax.net.ssl.HostnameVerifier(){
		 
			        public boolean verify(String hostname,
			                javax.net.ssl.SSLSession sslSession) {
			        	System.out.println("Verify hostname.. for  "+hostname);
			            if (hostname.equals("localhost")) {
			                return true;
			            }
			            return false;
			        }
			    });
			 

		
		System.setProperty("javax.net.ssl.trustStore", "d:/myKeys");
		System.setProperty("javax.net.ssl.trustStorePassword", "myData");


		URL getUrl = new URL("https://localhost:8443/REST-Secured-Service/jsonProducts/get");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();

		// Get json structure of customer object from the service

		connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
	 
		System.out.println("The response code is  "
				+ connection.getResponseCode());

		System.out.println("The response content type is "
				+ connection.getContentType());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		System.out.println("\nThe JSON format of customer sent by server...\n");

		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		connection.disconnect();
	}

		 

		 
}
