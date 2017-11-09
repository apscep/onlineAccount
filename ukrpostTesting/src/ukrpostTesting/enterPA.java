package ukrpostTesting;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class enterPA {
	WebDriver wd;
	String loginAbraam = "ukrpost@i.ua";
	String passwordAbraam = "446655";
    String ukrpostUrl = "http://ukrposhta.ua/";
	
	@Test
	public void EnterPersAcc() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver.exe");
	wd = new ChromeDriver();	
	wd.get(ukrpostUrl);
	Thread.sleep(1000);
	String parentWindow = wd.getWindowHandle();
	System.out.println(parentWindow);
	wd.findElement(By.xpath("html/body/div[4]/div/ul/li[3]/a")).click();
	Thread.sleep(2000);
	for (String childTab:wd.getWindowHandles()) {
		wd.switchTo().window(childTab);
	}
	Thread.sleep(2000);
	String bodyText = wd.findElement(By.tagName("body")).getText();
	Assert.assertEquals("До особистого кабінету", bodyText.contains("До особистого кабінету"));
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[1]/input")).sendKeys(loginAbraam);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/div[2]/input")).sendKeys(passwordAbraam);
	Thread.sleep(500);
	wd.findElement(By.xpath(".//*[@id='login-form-rcl']/form/input[1]")).click();
	Thread.sleep(2500);
	wd.getPageSource().contains("ОСОБИСТИЙ КАБІНЕТ");
	}
	
	

}
