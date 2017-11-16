package ukrpostTesting;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EnterPAChrome {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
    String ukrpostUrl = "http://ukrposhta.ua/";
    
	@BeforeClass (description = "Start Browser")
    public void RunBrowser () {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver.exe");
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("--start-maximized");
	wd = new ChromeDriver(chromeOptions);
	
	
	}
	@Test (description = "This test will check condition of web site")
	public void Loadsite () throws InterruptedException {
	wd.get(ukrpostUrl);	
	Thread.sleep(1000);
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "https://ukrposhta.ua/");
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
	@Test (dependsOnMethods="EnterPersAcc", description = "This test will login personal account")
	public void LoginToPa() throws InterruptedException {
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[1]/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[2]/input")).sendKeys(passwordAbraam);
	Thread.sleep(500);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/input[1]")).click();
	Thread.sleep(2500);
	Assert.assertTrue(wd.findElement(By.xpath(".//*[@id='primary']/div[2]/div[1]/div[2]/p[2]/a")).isDisplayed());
	}
	@Test (dependsOnMethods="LoginToPa", description = "This test will log out from personal account")
	public void LogoutPa () throws InterruptedException {
	Thread.sleep(1000);	
	wd.findElement(By.xpath(".//*[@id='primary']/div[2]/div[1]/div[2]/p[2]/a")).click();
	
	}
	@Test (priority = 7, description = "Check logout")
	public void checkingLogout () throws InterruptedException {
	Thread.sleep(2000);
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/login?loggedout=true");

		}
	
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
