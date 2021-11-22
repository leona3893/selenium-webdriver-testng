package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Css_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
				
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
						
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("lena@123234");
		driver.findElement(By.id("pass")).sendKeys("12345678");
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_Login_Password_Less_Than_6_Chars() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("lena@yopmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Login_Incorrect_Email_Or_Password() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("lena@yopmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']//span[text()='Invalid login or password.']")).isDisplayed());
		
		//div[@class='account-login']//span[text()='Invalid login or password.']
	}
	
	@Test
	public void TC_05_Create_New_Account() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("tech");
		driver.findElement(By.id("middlename")).sendKeys("panda");
		driver.findElement(By.id("lastname")).sendKeys("panda");
		driver.findElement(By.id("email_address")).sendKeys("techpanda2@yopmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
		
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_Login_Valid_Email_And_Password() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();			
		}
		
		
	}
}