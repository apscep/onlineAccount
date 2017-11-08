package ukrpostTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class enterPA {
	WebDriver wd;
	
	@Test
	
	public void EnterPersAcc() {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver.exe");
	wd = new ChromeDriver();	
		
	}
	
	

}
