package helperMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	private FileInputStream getExcelInputStream(String PartialPath) {
		FileInputStream ExelFile = null;

		/* Get the file from the given path */
		String ExcelPath = System.getProperty("user.dir") + PartialPath;
		File SrcFile = new File(ExcelPath);

		/* Make the file as input stream */
		try {
			ExelFile = new FileInputStream(SrcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Test Excel file is not found."+ e.getMessage());
			System.exit(0);
		}

		return ExelFile;
	}

	public Object [][] getExcelData(String PartialPath, int SheetNum , int ColsNum){
		/* Make the given excel sheet as input stream file */
		FileInputStream ExelFile = getExcelInputStream(PartialPath);
		XSSFWorkbook UserWB = null;

		try {
			/* Make workbook object to the given excel sheet*/
			UserWB = new XSSFWorkbook(ExelFile);
		} catch (IOException e) {
			System.out.println("Error while reading data from Excel sheet " + e.getMessage());
		}

		/* Select the desired sheet to start working on */
		XSSFSheet sheet = UserWB.getSheetAt(SheetNum);

		/* Get the total number of the rows */
		int RowsNum = sheet.getLastRowNum()+1;

		String [][] ExcelData = new String[RowsNum][ColsNum];
		XSSFRow row =null;

		/* Traverse through excel rows and columns and fetch the data
		 * Into the 2D array
		 */
		for (int RowsIndex = 0; RowsIndex < RowsNum; RowsIndex++) 
		{
			for (int ColsIndex = 0; ColsIndex < ColsNum; ColsIndex++) {
				/* Select the current row */
				row = sheet.getRow(RowsIndex);
				/* fetch the data from the current column */
				ExcelData[RowsIndex][ColsIndex] = row.getCell(ColsIndex).toString(); 
			}
		}

		try {
			UserWB.close();
		} catch (IOException e) {
			System.out.println("Error while closing the workbook " + e.getMessage());
		}


		return ExcelData;
	}
}
