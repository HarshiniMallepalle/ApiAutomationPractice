package com.qa.api;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAutomationP {

	String sHosturl = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	String sEPLogin = "/login";
	String sEPAddData = "/addData";
	String sURI = "";
	String token;
	Response response;

	@Test
	public void login() {
		sURI = sHosturl + sEPLogin;
		System.out.println(sURI);
		RestAssured.baseURI = sURI;
		response = RestAssured.given().body("{\"username\": \"harshinim0802@gmail.com\", \r\n"
				+ "\"password\": \"harshinim123\"}").contentType("application/json").post();
	
	int status =response.getStatusCode();
	System.out.println("Login Status : "+status);
	//System.out.println(response.asString());
	//System.out.println(response.asPrettyString());
	response.prettyPrint();
	
	token=response.jsonPath().get("token[0]");
	System.out.println(token);
	if(status==201) {
		System.out.println("Login sucess");
	}else {
		System.out.println("Login Not Sucess");
	}
	
	
	//AddData
	sURI = sHosturl + sEPAddData;
	RestAssured.baseURI = sURI;
	HashMap<String,String> headerList = new HashMap<String,String>();
	headerList.put("contentType","application/json");
	headerList.put("token", token);
	response=RestAssured.given().body("{\"accountno\": \"3112080204\", \r\n"
			+ "\"departmentno\": \"4\", \r\n"
			+ "\"salary\": \"6666\", \r\n"
			+ "\"pincode\": \"583103\"}").headers(headerList).post();
	
	response.prettyPrint();
	}
}
