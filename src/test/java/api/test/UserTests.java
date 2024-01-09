package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	 static Faker faker;
     static User userPayload;
	
	@BeforeClass
    public static void setupData() {
		
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

    }
	
	
	@Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
	
	
	@Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
	
	
	@Test(priority = 3)
    public void testUpdateUserByName() {
        // Update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        // Send the update request
        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        
        // Assert the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

     // Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
        responseAfterUpdate.then().log().body();

        // Assert the response status code
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

        // Optionally, add assertions to check the updated data
        // For example:
        //Assert.assertEquals("UpdatedFirstName", responseAfterUpdate.jsonPath().getString("firstName"));
        //Assert.assertEquals("UpdatedLastName", responseAfterUpdate.jsonPath().getString("lastName"));
        //Assert.assertEquals("UpdatedEmail", responseAfterUpdate.jsonPath().getString("email"));

    }
	
	
	
	@Test(priority = 4)
    public void testDeleteUserByName() {
        
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());

       
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Optionally, you can add assertions or verifications to check the response body
        // For example:
        //Assert.assertEquals("DeletedUserMessage", response.jsonPath().getString("message"));
    }

}
