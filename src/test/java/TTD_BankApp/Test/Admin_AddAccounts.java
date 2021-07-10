package TTD_BankApp.Test;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class Admin_AddAccounts extends webDriverStarter{

		
	@BeforeTest(groups = "Addaccount")
	public void Setup() {
		driver.get(driverSingleton.getURL());
	}
	
	@DataProvider(name="testData")
	public static Object[][] userData()
	{
		return new Object[][] {
			{"Eslam" , "Ehab","55"},
			{"Eslam","Aboutaleb","123"}
		};
	}
	
	@Test(priority = 0 , groups = "Addaccount")
	void AdminLogin() {
		
		WebElement AdminLoginBtn = driver.findElement(By.xpath("//button[normalize-space()='Bank Manager Login']"));
		AdminLoginBtn.click();
	}
	
	@Test(dependsOnMethods = "AdminLogin" , groups = "Addaccount")
	void AddCustomer(){
		WebElement AddAccountBtn = driver.findElement(By.xpath("//button[normalize-space()='Add Customer']"));
		AddAccountBtn.click();
		
	}
	
	@Test(dependsOnMethods = "AddCustomer" ,groups = "Addaccount" ,dataProvider="testData")
	void EnterCustomerData(String FName, String LName, String Code) {
		String ExpectedAlertText = "Customer added successfully with customer id";
		
		WebElement FirstName	= driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		WebElement LastName		= driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		WebElement PostCode		= driver.findElement(By.xpath("//input[@placeholder='Post Code']"));
		WebElement AddBtn		= driver.findElement(By.xpath("//button[@type='submit']"));
		/* Make sure fields are clear*/
		FirstName.clear();
		LastName.clear();
		PostCode.clear();
		
		/* Enter provided data*/
		FirstName.sendKeys(FName);
		LastName.sendKeys(LName);
		PostCode.sendKeys(Code);
		
		/*Submit Data*/
		AddBtn.submit();
		
		Alert alert = driver.switchTo().alert();
		String AlertText = alert.getText();
		
		if(AlertText.contains(ExpectedAlertText) == false) {
			throw new NoAlertPresentException("The current alert text" + AlertText);
		}
		
		alert.accept();
	}
	
}

