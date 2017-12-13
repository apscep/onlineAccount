package ukrpostTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EnterPaNegativeMozila {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "4466551";
    String ukrpostUrl = "http://ukrposhta.ua/";
    
	@BeforeClass (description = "Start Browser")
    public void RunBrowser () {
	System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Tester stuff\\geckodriver.exe");
	wd = new FirefoxDriver();
	
	
	}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () throws InterruptedException {
	
		
	wd.get(ukrpostUrl);
	Thread.sleep(1000);
	}
	
	@Test (dependsOnMethods="Loadsite", description = "This test will enter personal account")
	public void EnterPersAcc() throws InterruptedException {
	String parentWindow = wd.getWindowHandle();
	System.out.println(parentWindow);
	wd.findElement(By.xpath("html/body/div[4]/div/ul/li[3]/a")).click();
	Thread.sleep(2000);
	//Switch to new tab
	for (String childTab:wd.getWindowHandles()) {
		wd.switchTo().window(childTab);
				}
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "https://ukrposhta.ua/login");
	Thread.sleep(2000);
	}
	
	@Test (dependsOnMethods="EnterPersAcc", description = "This test will login personal account and failed")
	public void LoginToPa() throws InterruptedException {
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[1]/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[2]/input")).sendKeys(passwordAbraam);
	Thread.sleep(500);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/input[1]")).click();
	Thread.sleep(2000);
	String expectedErrorMessage = wd.findElement(By.xpath(".//*[@id='login-form-rcl']/span")).getText();
	String actualErrorMessage = "Ћог≥н або пароль не в≥рн≥!";
	Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	}
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
		}
}
