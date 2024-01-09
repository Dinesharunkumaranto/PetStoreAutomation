package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
	    
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName); // Fixed typo in the method name
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		
		// Making a request to create a user using the API endpoint
		Response response = UserEndPoints.createUser(userPayload);

		// Asserting that the response status code is equal to 200
		Assert.assertEquals(response.getStatusCode(), 200);

		
		
	}
	
	
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {
	    // Making a request to delete a user by username using the API endpoint
	    Response response = UserEndPoints.deleteUser(userName);

	    // Asserting that the response status code is equal to 200
	    Assert.assertEquals(response.getStatusCode(), 200);
	}



}
