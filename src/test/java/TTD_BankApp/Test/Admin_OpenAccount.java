package TTD_BankApp.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import helperMethods.ReadExcel;

public class Admin_OpenAccount extends webDriverStarter{

	@DataProvider(name="ExcelData")
	public Object[][] userRegisterData()
	{
		String ExcelPartialPath = "/TDD/OpenAccountsTDD.xlsx";
		/* get data from Excel Reader class  */
		ReadExcel Excelfile = new ReadExcel();
		return Excelfile.getExcelData(ExcelPartialPath, 0, 2);
	}
	
	
	@Test(priority=0,alwaysRun=true,dataProvider="ExcelData")
	void OpenAccount(String Name,String Currency) {
		String ExpectedAlertMsg = "Account created successfully with account Number";
		/* Open "Open Account" page */
		WebElement OpenAccountBtn = driver.findElement(By.xpath("//button[normalize-space()='Open Account']"));
		OpenAccountBtn.click();
		
		/* Select user */
		Select userSelect= new Select(driver.findElement(By.id("userSelect")));
		userSelect.selectByVisibleText(Name);
		
		/* Select Currency type */
		Select CurrencySelect= new Select(driver.findElement(By.id("currency")));
		CurrencySelect.selectByVisibleText(Currency);
		
		/* Submit the data */
		WebElement ProcessBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		ProcessBtn.submit();
		
		/* Check the alert */
		Alert alert = driver.switchTo().alert();
		String AlertText = alert.getText();
		if(AlertText.contains(ExpectedAlertMsg) == false)
		{
			throw new NoAlertPresentException("The current Alert message is " + AlertText);
		}
		
		alert.accept();
	}
}
