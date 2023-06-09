package UIANDRESTASSURED;

import static io.restassured.RestAssured.given;
//import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import POJOREQRES.Getresponse;
import POJOREQRES.POJOResponse;
import POJOREQRES.PatchRequest;
import POJOREQRES.PatchResponsePojo;
import POJOREQRES.PostMethod;
import POJOREQRES.PutPOJO;
import POJOREQRES.PutResponsePojo;
import POM.WEBReposInXpath;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostmethodResponse {

	POJOResponse responsePOJO;
	Getresponse getResponse;
	PutResponsePojo putresponsePOJO;
	PatchResponsePojo patchresponsePojo ;
	WebDriver driver;
	
	
	
	//UI AUTOMATION TestCases
	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} 
	}

	@Test(priority = 1)

	public void launchBrowser(@Optional("chrome") String browser) throws InterruptedException {

		System.setProperty("webdriver.Chrome.driver", "C:\\Users\\Tutorbin\\Desktop\\Selenium\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

		WEBReposInXpath wrix = new WEBReposInXpath(driver);
		wrix.SerachREQRES();
		Thread.sleep(3000);
		wrix.SelectReqres();
		wrix.SelectReqresHost();
		Thread.sleep(3000);
		driver.quit();

	}
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
}
	
	//API AUTOMATION Test Cases Below Down
	

	@Test(priority = 2)
	@Parameters("environment")

	public void PostMethodResponseCreatedSuccessfully() {

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

	@Test(priority = 3)
	@Parameters("environment")

	public void GetmethodResponse() {

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON).build();

		Getresponse getResponse = new Getresponse();

		getResponse.setPage(0);
		getResponse.setPer_page(0);

		getResponse.setTotal(0);

		getResponse.setTotal_pages(0);
		getResponse.setSupport(null);
		getResponse.setData(null);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(getResponse);
		getResponse = createOrderReq.when().get("/api/users?page=2").then().log().all().extract().response().body()
				.as(Getresponse.class);

		Assert.assertEquals(getResponse.getTotal(), 12);

	}

	@Test(priority = 4)

	public void PostMethodResponse() {

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON).build();

		PutPOJO pj = new PutPOJO();
		pj.setName("nitin");
		pj.setJob("CSE");

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(pj);
		putresponsePOJO = createOrderReq.when().put("/api/users/2").then().log().all().extract().response().body()
				.as(PutResponsePojo.class);

		Assert.assertEquals(putresponsePOJO.getName(), "nitin");
	}
	
	@Test(priority=5)
	
	public void PatchMethodResponse() {
		
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON).build();
		
		PatchRequest patchrequest = new PatchRequest();
		patchrequest.setName("john");
		patchrequest.setJob("whatever");
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(patchrequest);
		 patchresponsePojo = createOrderReq.when().patch("/api/users/2").then().log().all().extract().response().body()
				.as(PatchResponsePojo.class);

		Assert.assertEquals(patchresponsePojo.getJob(), "whatever");
	}
	
	
	

}
