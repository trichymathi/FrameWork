package ReusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class ReusableMethodsClass {
	WebDriver driver;
	public void intializeDriver() throws IOException {
		try {
		Properties config = new Properties();
		String configFilePath = System.getProperty("user.dir")+"\\PropertyFiles\\Config.properties";
		FileInputStream file = new FileInputStream(configFilePath);
		config.load(file);
		file.close();
		String driverName = config.getProperty("driver.name");
		String driverPath =config.getProperty("driver.Path");
		String mode =config.getProperty("running.Mode");
		switch(driverName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();
			if(mode.equalsIgnoreCase("headless")) {
			 options.addArguments("--headless");
			 options.addArguments("--disable-notifications");
			}else {
				options.addArguments("--disable-notifications");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			break;
		case "edge" :
			System.setProperty("webdriver.edge.driver", driverPath);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeOptions);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			break;
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", driverPath);
			FirefoxOptions firFoxOptions = new FirefoxOptions();
			firFoxOptions.addArguments("--disable-notifications");
			driver = new FirefoxDriver(firFoxOptions);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			break;
			default : System.out.println("No driver Name Not Matched");
		}
		}catch(Exception e) {
			System.out.println("Check Your Internet Connection");
		}
		
	}
	
	public void launchUrl(String url) {
		driver.get(url);
	}
	
	public void screenShot(String name) throws IOException {
		ExtentSparkReporter Reporter = new ExtentSparkReporter("Report.html");
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(Reporter);
//		extent.createTest("Web Application").info(name).addSc
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot, new File(name+".png"));
		extent.flush();
	}

	public void clickOnElement(String xpath) {
		try {
		WebElement element = driver.findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		until.click();
		}catch(Exception e) {
		System.err.println("Element Unable To Click");
		NoSuchElementException exception=new NoSuchElementException("Element Not Found");
		exception.getMessage();
		e.printStackTrace();
		}
	}
	
	public void typeInElement(String xpath,String typeValue,boolean clearCondition) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
		if(clearCondition == true) {
			element.clear();
		}
		element.sendKeys(typeValue);
		}catch(Exception e) {
			System.err.println("Unable to pass the parameter");
			e.printStackTrace();
		}
	}
	
	public void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public String getText(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		String getText=element.getText();
		return getText;
	}
	
	public void waitUntillElementVisible(String xpath,long timouts) {
		WebElement element = driver.findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timouts));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void quitBrowser() {
		driver.quit();
	}
	
		
	
}
