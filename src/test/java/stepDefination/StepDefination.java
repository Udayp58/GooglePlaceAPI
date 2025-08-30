package stepDefination;

import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination {
	RequestSpecification Request;
	Response Res;
	TestDataBuild data = new TestDataBuild();
	Utils util = new Utils();
	JsonPath js;
	static String Place_Id;

	@Given("Add Place API Payload with {string} and {string}")
	public void add_place_api_payload_with_and(String name, String address) throws IOException {
		Request = given().spec(util.requestSprcification()).body(data.AddPlacePaylaod(name, address));

	}

	@When("User hit {string} with http {string} request")
	public void user_hit_with_http_request(String resource, String method) {
		APIResource resourceAPI = APIResource.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		System.out.println("This new Line Added");
		System.out.println("This new line added by uday");

		if (method.equalsIgnoreCase("POST")) {
			Res = Request.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			Res = Request.when().get(resourceAPI.getResource());
		}

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(Res.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {

		assertEquals(util.getKeyValue(Res, key), expectedValue);

	}

	@Then("verify place_id got maps to {string} Using {string}")
	public void verify_place_id_got_maps_to_using(String expectedName, String resource) throws IOException {
		Place_Id = util.getKeyValue(Res, "place_id");
		Request = given().spec(util.requestSprcification()).queryParam("place_id", Place_Id);
		user_hit_with_http_request(resource, "GET");
		String actualName = util.getKeyValue(Res, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("Delete Palce API Payload")
	public void delete_palce_api_payload() throws IOException {
		Request = given().spec(util.requestSprcification()).body(data.deletePlacePayload(Place_Id));
	}

}
