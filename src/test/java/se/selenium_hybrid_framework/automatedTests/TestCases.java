package se.selenium_hybrid_framework.automatedTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static se.selenium_hybrid_framework.utils.Constant.*;

import se.selenium_hybrid_framework.actionModules.FindFlightsAction;
import se.selenium_hybrid_framework.utils.ExcelUtils;
import se.selenium_hybrid_framework.utils.FileNameUtil;

public class TestCases {
	static WebDriver driver;
	
	@BeforeClass
	public static void config() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		
		//Set up input and output excel files
		ExcelUtils.setExcelInputFile(EXCEL_INPUT_FILEPATH, EXCEL_DATA_SHEET_NAME);
		ExcelUtils.createExcelOutputFile(EXCEL_OUTPUT_FOLDER + FileNameUtil.getFileName(FILE_NAME_FORMAT), EXCEL_DATA_SHEET_NAME);
		
		//Write the headers in the output-file
		ExcelUtils.writeRowToOutputFile(OUT_DATA_HEADERS);
	}
	
	@BeforeMethod
	public static void setUp() {
		driver = new ChromeDriver();
	}
	
	/*
	 * DataProvider addressData
	 * Returns a two-dimensional array of strings, one for each row in the input-file
	 * Each string in a particular row array will become an input variable to the beneficiary test case.
	 */
	@DataProvider(name = "testData")
	public static Object[][] testData(Method method){
		Object[][] testInput = ExcelUtils.getTestData(method.getName(), NUMBER_OF_IN_PARAMS);
		return testInput;
	}

	/*
	 * geocodeTest
	 * Performs a test with the provided search data and 
	 * appends the resulting data row to the output-file
	 */
	@Test(dataProvider = "testData")
	public void test(String column_1, String column_2, String column_3, String column_4) {   
		
		driver.get(URL);
		FindFlightsAction.execute(driver, column_3, column_4);
		ExcelUtils.writeRowToOutputFile(new String[]{column_1, column_2, column_3, column_4});
    }
	
	@AfterMethod
	public static void tearDown() {
		driver.close();
	}
	
	@AfterClass
	public void cleanUp() {
	}
}

