package TTD_BankApp.Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class webDriverStarter {

	public static WebDriver driver = null;
	public DriverSingleton driverSingleton = null;


	@BeforeSuite
	public void setup() {
		driverSingleton = DriverSingleton.getInstance();
		driver = DriverSingleton.getDriver();	
	}

	@AfterSuite(alwaysRun=true)
	public void tearDown() {
		try {
			//driver.close();
			//driver.quit();
			if(driver != null)
			{
				driver = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
