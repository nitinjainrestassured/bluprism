package UIANDRESTASSURED;

import static io.restassured.RestAssured.given;
//import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import POJOREQRES.POJOResponse;
import POJOREQRES.PostMethod;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PostmethodResponse {

	POJOResponse responsePOJO;

	@Test(priority = 1)
	@Parameters("environment")

	public void PostMethodResponseCreatedSuccessfully(@Optional("test") String environment) {

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON).build();

		PostMethod methodPost = new PostMethod();

		methodPost.setName("morpheus");
		methodPost.setJob("leader");

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(methodPost);
		responsePOJO = createOrderReq.when().post("/api/users").then().log().all().extract().response().body()
				.as(POJOResponse.class);
		Assert.assertEquals(responsePOJO.getName(), "morpheus");

	}

}
