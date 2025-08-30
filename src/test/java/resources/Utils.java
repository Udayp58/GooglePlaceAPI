package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification spec;

	public RequestSpecification requestSprcification() throws IOException {
		if (spec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			spec = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return spec;
		}
		return spec;

	}

	public String getGlobalValues(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(Key);

	}

	public String getKeyValue(Response response, String Key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(Key).toString();

	}

}
