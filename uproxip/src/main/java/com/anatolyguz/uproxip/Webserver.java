package com.anatolyguz.uproxip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Webserver {
	
	 public static String authenticate(Authenticate au ) throws IOException  {
		 	
		 	
		 	String post_url = App.SRV+"Authenticate";
		 	String post_params = getJSONString(au);  
		 	
		 	String s = post(post_url, post_params);
				
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			ID id = gson.fromJson(s, ID.class);
			System.out.println(id.UserSID);
			return id.UserSID;
				
 
	 }
	 
	 
	 
	 public static String employeegetlist(Emploeegetlist em ) throws IOException {
		
		String post_url = App.SRV+"EmployeeGetList";
		String post_params = getJSONString(em);  
		 
		String s = post(post_url, post_params);
		return s;
	 }
	 
	 public static String departmentgetall (Object obj ) throws IOException {
			
			String post_url = App.SRV+"DepartmentGetAll";
			String post_params = getJSONString(obj);  
			 
			String s = post(post_url, post_params);
			return s;
		 }
	 
	 

	 public static void logout(String UserSID ) throws IOException {
		System.out.println("UserSID="+UserSID);	
		String post_url = App.SRV+"Logout";
		String post_params = "{\"UserSID\":\""+UserSID+"\"}";
		
		
		System.out.println("post_url="+post_url);
		System.out.println("post_params="+post_params);
		
		String s = post(post_url, post_params);
		System.out.println(s);
		 
	 }
	 
	 
	 

	 public static  String post(String post_url, String post_params ) throws IOException  {

		 	String s = null;
		 	URL obj = new URL(post_url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(post_params.getBytes());
			os.flush();
			os.close();		
			// For POST only - END
			
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

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
				System.out.println("===========");
				System.out.println("post_url=" + post_url);
				System.out.println("anwer="+response.toString());
				System.out.println("===========");
				s = response.toString();
				
				
				
			} else {
				System.out.println("POST request not worked");
			}
			
			return s;
			
	 }
	 
	 
	 
	 
	 
	 private static String getJSONString(Object au){
		 Gson gson = new Gson();
		 return gson.toJson(au);
	 }
	 
	 private static String getJSON(String input){
		 Gson gson = new Gson();
		 return null;
	 }
	 
	 public class ID {
			String UserSID;
				
	 }
	 
	 

	 
	 
	 
}
