package com.dss.test.dataproviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import com.dss.test.properties.DSSProperties;
import com.dss.test.utilities.DSSUtilities;

/**
 * ------- DSSDataProvider ------- Author: QA-DART Created on: 20-May-2016
 * History of Changes: Data Provider for Subscription Tests
 */
public class DSSDataProvider {

	/* Excel Reader Method */
	public static String[][] excelreader(String Marketname, String Scnerio) throws IOException {
		int rowCount, colCount;
		File file = new File(DSSProperties.filePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet Excelsheet = workbook.getSheet(Marketname);
		rowCount = Excelsheet.getLastRowNum() + 1;
		colCount = Excelsheet.getRow(0).getLastCellNum();
		String[][] excelArray = new String[1][colCount];
		for (int i = 1; i < rowCount; i++) {
			if ((Excelsheet.getRow(i).getCell(0).toString()).equalsIgnoreCase(Scnerio)) {
				for (int j = 0; j < colCount; j++) {
					excelArray[0][j] = Excelsheet.getRow(i).getCell(j).toString();
					
				}
			}
		}

		return excelArray;
	}

	@DataProvider(name = "WithInAreaZIP SSORUser with CreditCard")
	public static Object[][] WithInAreaZIPSSORUserwithCreditCard() throws IOException {
		Object[][] arrayObject = excelreader("OS", "WithInAreaZIP SSORUser with CreditCard");
		return arrayObject;
	}

	@DataProvider(name = "OutOfAreaZIP SSORUser with BankAccount")
	public static Object[][] OutOfAreaZIPSSORUserwithBankAccount() throws IOException {
		Object[][] arrayObject = excelreader("OS", "OutOfAreaZIP SSORUser with BankAccount");
		return arrayObject;
	}

	@DataProvider(name = "WithInAreaZIP NONSSORUser with BankAccount")
	public static Object[][] WithInAreaZIPNONSSORUserwithBankAccount() throws IOException {
		Object[][] arrayObject = excelreader("OS", "WithInAreaZIP NONSSORUser with BankAccount");
		return arrayObject;
	}

	@DataProvider(name = "OutOfAreaZIP NONSSORUser with CreditCard")
	public static Object[][] OutOfAreaZIPNONSSORUserwithCreditCard() throws IOException {
		Object[][] arrayObject = excelreader("OS", "OutOfAreaZIP NONSSORUser with CreditCard");
		return arrayObject;
	}

	@DataProvider(name = "WithInAreaZIP NONSSORUser with CreditCard")
	public static Object[][] WithInAreaZIPNONSSORUserwithCreditCard() throws IOException {
		Object[][] arrayObject = excelreader("OS", "WithInAreaZIP NONSSORUser with CreditCard");
		return arrayObject;
	}

}
