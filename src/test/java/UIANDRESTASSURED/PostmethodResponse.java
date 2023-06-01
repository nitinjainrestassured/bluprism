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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import POJOREQRES.Getresponse;
import POJOREQRES.POJOResponse;
import POJOREQRES.PostMethod;
import POM.WEBReposInXpath;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostmethodResponse {

	POJOResponse responsePOJO;
	Getresponse getResponse;

	@Test(priority = 1)

	public void launchBrowser() throws InterruptedException {

		System.setProperty("webdriver.Chrome.driver", "C:\\Users\\Tutorbin\\Desktop\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
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

}
