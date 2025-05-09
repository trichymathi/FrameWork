
package ReusableMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class intialize_Selenium {
	
	 WebDriver driver;
	
//	public intialize_Selenium(WebDriver driver) {
//		this.driver=driver;
//	}
//    By username = By.xpath("//div");	
//	
//    public void userNameEnter() {
//		driver.findElement(username).click();
//	}
    
//	public intialize_Selenium(WebDriver driver) {
//		this.driver=driver;
//		PageFactory.initElements(driver,this);
//	}
//	
//	@FindBy(xpath="//div") WebElement element;	
//	public void loginPage() {
//		element.click();
//	}
	
	
	@SuppressWarnings("deprecation")
	public void intializeDriver() throws IOException {
		Properties config = new Properties();
		String configFilePath = System.getProperty("user.dir")+"\\PropertyFiles\\Config.properties";
		FileInputStream file = new FileInputStream(configFilePath);
		config.load(file);
		file.close();
		String driverName = config.getProperty("driver.name");
		String driverPath =config.getProperty("driver.Path");
		switch(driverName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--headless");
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
		
		
	}


	

}
