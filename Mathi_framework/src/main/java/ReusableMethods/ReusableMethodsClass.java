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
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	
	public void launchBrowser()   {
		BrowserDriverFactory browserDriverFactory = new BrowserDriverFactory();
		try {
			driver=browserDriverFactory.intializeDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void launchUrl(String url) {
		driver.get(url);
	}
	
	protected WebElement locatorFind(By locator) {
		return driver.findElement(locator);
	}
	
	protected WebElement find(By locator) {
		WebElement element = locatorFind(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		return until;
	}
	
	public void screenShot(String name) throws IOException {
		ExtentSparkReporter Reporter = new ExtentSparkReporter("Report.html");
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(Reporter);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot, new File(name+".png"));
		extent.flush();
	}

	public void clickOnElement(String xpath) {
		try {
		WebElement elementToClick = find(By.xpath(xpath));
		elementToClick.click();
		}catch(NoSuchElementException e) {
		System.err.println("Element Unable To Click");
		e.getMessage();
		}
	}
	
	public void typeInElement(String xpath,String typeValue,boolean clearCondition) {
		try {
			WebElement element = find(By.xpath(xpath));
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
		WebElement element = find(By.xpath(xpath));
		return element.getText();
		
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
