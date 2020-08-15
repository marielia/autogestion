package com.asecor.extranet.seguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionAsecor {
	
	private     String USER_AGENT = "Mozilla/5.0";

	private     String GET_URL = "http://200.80.28.114:9595/asecoraminghomeOLTest";

	private     String POST_URL = "http://200.80.28.114:9595/asecoraminghomeOLTest/index.html";

	private     String POST_PARAMS = "oluserid=90&olagencyid=1819";

	 

	public   void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

	public   String sendPOST() throws IOException {
		URL obj = new URL("http://200.80.28.114:9595/tpgaminghomeOLTest/index.html");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT); 
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write("oluserid=90&olagencyid=1819".getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode+" " +con.getResponseMessage());

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println("response "+response.toString());
			return response.toString();
		} else {
			System.out.println("POST request not worked");
			return "";
		}
		
		
	}
}
