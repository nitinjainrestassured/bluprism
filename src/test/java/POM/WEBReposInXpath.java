package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WEBReposInXpath {
	WebDriver driver;
	
	
	public WEBReposInXpath(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private By serach=By.xpath("//textarea[@aria-autocomplete='both']");
	private By selectReqres=By.xpath("//span[.='reqres.in']");
	private By selectReqreshostedAPi=By.xpath("//h3[contains(.,'Reqres - A hosted REST-API ready to respond to your AJAX ..')]");
	
	
	
	
	public void SerachREQRES() {
		
     driver.findElement(serach).sendKeys("Reqres.in");
	}
	
	public void  SelectReqres() {
		
		driver.findElement(selectReqres).click();
	}
	
	public void SelectReqresHost() {
		
		driver.findElement(selectReqreshostedAPi).click();
	}
}

