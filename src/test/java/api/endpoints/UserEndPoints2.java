package api.endpoints;

import io.restassured.response.Response;

import org.testng.annotations.Test;

import com.beust.jcommander.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * 1. UserEndPints. java
   2. Created for perform Create, Read, Update, Delete requests t the user API
   
 * 
 */

public class UserEndPoints2 {
	
	// Method created for getting URLs from properties file
    static java.util.ResourceBundle getURL() {
        java.util.ResourceBundle routes = java.util.ResourceBundle.getBundle("routes"); // Load properties file
        return routes;
    }
	
	
    
	
	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");

		
		
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
            .when()
                .post(Routes.post_url);
        return response;
    }
	
	
	public static Response readUser(String userName) {
		
		String get_url = getURL().getString("get_url");
		
        Response response = given()
                .pathParam("username", userName)
            .when()
                .get(Routes.get_url);
        return response;
    }
	
	
	 public static Response updateUser(String userName, User payload) {
		 
		 String update_url = getURL().getString("update_url");
		 
		 
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .pathParam("username", userName)
	                .body(payload)
	            .when()
	                .put(Routes.update_url);
	        return response;
	    }
	 
	 
	 public static Response deleteUser(String userName) {
		 
		 String delete_url = getURL().getString("delete_url");
		 
		 
	        Response response = given()
	                .pathParam("username", userName)
	            .when()
	                .delete(Routes.delete_url);
	        return response;
	    }
	

}
