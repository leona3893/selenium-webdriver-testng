package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Css_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//sleepInSecond(2);
		//testcase không gọi testcase
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
		

		
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Lena");
		driver.findElement(By.id("txtEmail")).sendKeys("Lena@1234@1234");
		driver.findElement(By.id("txtCEmail")).sendKeys("Lena@1234@1234");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Lena");
		driver.findElement(By.id("txtEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("test@yopmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
										
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	@Test
	public void TC_04_Register_Password_Less_Than_6_Chars() {	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Lena");
		driver.findElement(By.id("txtEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	@Test
	public void TC_05_Register_Incorrect_Confirm_Password() {	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Lena");
		driver.findElement(By.id("txtEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Register_Invalid_Phone_Number() {	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Lena");
		driver.findElement(By.id("txtEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("testalada@yopmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("1234567890");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
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