package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlaceAPI")
	public void beforeDeletePlaceAPI() throws IOException
	{
		StepDefination 	sd = new StepDefination();
		sd.add_place_api_payload_with_and("Riyansh" , "Londan");
		sd.user_hit_with_http_request("AddPlaceAPI", "Post");
		sd.verify_place_id_got_maps_to_using("Riyansh", "GetPlaceAPI");
		System.out.println("This method executed");
		
	}

}
