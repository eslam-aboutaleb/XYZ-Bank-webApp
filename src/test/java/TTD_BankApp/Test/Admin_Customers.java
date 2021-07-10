package TTD_BankApp.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Admin_Customers extends webDriverStarter {

	@Test(priority = 0)
	void FindCustomer() {
		/* Open customers page */ 
		WebElement CustomersBtn = driver.findElement(By.xpath("//button[normalize-space()='Customers']"));
		CustomersBtn.click();
		
		WebElement SearchBox = driver.findElement(By.xpath("//input[@placeholder='Search Customer']"));
		SearchBox.clear();
		SearchBox.sendKeys("Hermoine");
	}
}
