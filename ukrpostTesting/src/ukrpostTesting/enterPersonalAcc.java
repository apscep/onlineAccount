package ukrpostTesting;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class enterPersonalAcc 
{
	WebDriver wd;
	
	
@Test 
 public void startApp() throws InterruptedException
 {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver.exe");
	wd= new ChromeDriver();
	wd.get("http://www.i.ua/");
	Thread.sleep(1000);
	String currentURL= wd.getCurrentUrl();
	Assert.assertTrue(currentURL.contains("http://www.i.ua/"));
	 
 }	
@Test (dependsOnMethods="startApp")
public void ExecuteApp() throws InterruptedException
{
	wd.findElement(By.xpath("html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/ul/li[1]/p[2]/input")).sendKeys("xxxapxxx");
	wd.findElement(By.xpath("html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/ul/li[1]/input")).sendKeys("6756789");
	wd.findElement(By.xpath("html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/p/input")).click();
	Thread.sleep(2000);
	boolean status=wd.findElement(By.xpath("html/body/div[1]/div[5]/div[1]/div[1]/p/a")).isDisplayed();
	Assert.assertTrue(status);
}
@Test	(dependsOnMethods="ExecuteApp")
public void LogouttApp() throws InterruptedException
{
	wd.findElement(By.xpath(".//*[@id='header_overall']/div[1]/ul[3]/li[4]/span")).click();
	Thread.sleep(1000);
	wd.findElement(By.xpath(".//*[@id='accountSettingsPopup']/ul/li[7]/a")).click(); 
	Thread.sleep(1000);
	Assert.assertTrue(wd.findElement(By.xpath("html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/h2/a")).isDisplayed());
}
@AfterClass
public void CloseBrowser(){
	wd.close();
}
}
