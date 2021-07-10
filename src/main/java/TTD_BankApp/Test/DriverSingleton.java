package TTD_BankApp.Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

import helperMethods.ReadConfigPropertiesfile;

public class DriverSingleton {
	private static DriverSingleton instance = null;
	private static WebDriver driver = null;
	private int implicitWaitInSec = 0;
	private String WebsiteUrl = "";
	
	/* DriverSingleton constructor */
	private DriverSingleton() {
		ReadConfigPropertiesfile config = new ReadConfigPropertiesfile();
		implicitWaitInSec = config.getImplicitWait();
		instantiate(config.getBrowser());
		WebsiteUrl = config.getURL();
	}
	
	/* Initializes the web driver*/
	@SuppressWarnings("deprecation")
	public WebDriver instantiate(String Strategy) {
		DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(Strategy);
		driver = driverStrategy.setStrategy();
		/* Configures general timeout*/
		driver.manage().timeouts().implicitlyWait(implicitWaitInSec, TimeUnit.SECONDS);
		/* Maximizes the driver window*/
		driver.manage().window().maximize();
		return driver;
	}
	
	/* Gets only one instance of singleton or return the current instance 
	 * if there is already one*/
	public static DriverSingleton getInstance() {
		if(instance == null){
			instance = new DriverSingleton();
		}
		
		return instance;
	}
	
	/* Makes the current instance is null and quits driver*/
	public static void closeObjectInstance() {
		instance = null;
		driver.quit();
	}
	
	/* Gets the current driver*/
	public static WebDriver getDriver() {
		return driver;
	}
	
	public String getURL() {
		return WebsiteUrl;
	}
}
