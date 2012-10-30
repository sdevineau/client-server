package com.emn.app.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Post {

	public static final String ADDRESS = "http://82.67.139.156:8080/RemoteCompiling/BuildFromSource";
	public static final String SRC = "public class Test { \n" + 
     "	public static void main(String[] args) { \n" +
     "		System.out.println(\"zaza\"); \n " +
     "	}\n" + 
     "}\n";
	
	public static final String POST_SRC = "sourceCode";
	public static final String POST_TECHNO = "technology";
	public static final String POST_FILENAME = "fileName";

	public static String remoteCompiling(String technology, String sourceCode,
			String fileName) throws UnsupportedEncodingException {
		StringBuilder data = new StringBuilder();
		InsertNewValueInDataStream(data, POST_FILENAME, fileName);
		InsertNewValueInDataStream(data, POST_TECHNO, technology);
		InsertNewValueInDataStream(data, POST_SRC, sourceCode);
		return PostData(data.toString(), ADDRESS);
	}

	public static String PostData(String data, String urlString) {
		System.out.println(data);
		StringBuilder response = new StringBuilder();
		try {
			// Send data
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setAllowUserInteraction(true);
			((HttpURLConnection)connection).setRequestMethod("POST");
			connection.setUseCaches(false);
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    connection.setRequestProperty("Content-Length", ""+ data.getBytes().length);

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());
			output.write(data.getBytes());
			output.close();
			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				response.append((char) c);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static void InsertNewValueInDataStream(StringBuilder data,
			String key, String value) throws UnsupportedEncodingException {
		// If it is the first member so there is no & but else there is one
		data.append(((data.length() == 0) ? "" : "&")
				+ URLEncoder.encode(key, "UTF-8") + "="
				+ URLEncoder.encode(value, "UTF-8"));
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(remoteCompiling("java", SRC, "Test"));
	}
}