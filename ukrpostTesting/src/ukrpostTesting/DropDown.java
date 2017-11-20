package ukrpostTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class DropDown {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
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
	String currentUrl = wd.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://ukrposhta.ua/");
	wd.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[2]/span")).click();
	Thread.sleep(200);
	wd.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[2]/ul/li[1]/a")).click();
	Thread.sleep(1200);
		}
	@Test (description = "This test will check dropdown page")
	public void CheckElement () throws InterruptedException {
	String ExpectedText = wd.findElement(By.xpath("html/body/div[1]/div[2]/div/div/h3")).getText();
	String ActualText = "Укрпошта Експрес";
	Assert.assertEquals(ExpectedText, ActualText);
	}
	@AfterClass
	public void CloseBrowser(){
		wd.quit();
	}
}
