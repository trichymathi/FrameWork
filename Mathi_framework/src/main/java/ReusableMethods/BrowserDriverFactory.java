
package ReusableMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory {
	
	private ThreadLocal<WebDriver> driver =new ThreadLocal<WebDriver>();
	 
	protected WebDriver intializeDriver() throws IOException {
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
			driver.set(new ChromeDriver(options));
			break;
		case "edge" :
			System.setProperty("webdriver.edge.driver", driverPath);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			driver.set(new EdgeDriver(edgeOptions));
			break;
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", driverPath);
			FirefoxOptions firFoxOptions = new FirefoxOptions();
			firFoxOptions.addArguments("--disable-notifications");
			driver.set(new FirefoxDriver(firFoxOptions)); ;
			break;
		default : 
		    System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions optionsdefault = new ChromeOptions();
			optionsdefault.addArguments("--disable-notifications");
			optionsdefault.addArguments("--headless");
			if(mode.equalsIgnoreCase("headless")) {
				optionsdefault.addArguments("--headless");
				optionsdefault.addArguments("--disable-notifications");
				}else {
					optionsdefault.addArguments("--disable-notifications");
				}
				driver.set(new ChromeDriver(optionsdefault));
			break;
		}	
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver.get();
	}
}


//public intialize_Selenium(WebDriver driver) {
//this.driver=driver;
//}
//By username = By.xpath("//div");	
//
//public void userNameEnter() {
//driver.findElement(username).click();
//}

//public intialize_Selenium(WebDriver driver) {
//this.driver=driver;
//PageFactory.initElements(driver,this);
//}
//
//@FindBy(xpath="//div") WebElement element;	
//public void loginPage() {
//element.click();
//}