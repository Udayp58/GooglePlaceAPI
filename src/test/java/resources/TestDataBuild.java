package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public  AddPlace AddPlacePaylaod(String name, String address) {
		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setName(name);
		a.setPhone_number("9420055676");
		a.setWebsite("http://google.com");
		a.setLanguage("Marathi");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setTypes(myList);
		return a;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
