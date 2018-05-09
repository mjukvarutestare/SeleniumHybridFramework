package se.selenium_hybrid_framework.utils;

public class Constant {
	public static final String URL = "http://www.blazedemo.com";
	public static final String DRIVER_PATH = ".\\drivers\\chromedriver.exe";
	public static final String EXCEL_INPUT_FILEPATH = ".\\data\\input\\testdata.xlsx";
	public static final String EXCEL_OUTPUT_FOLDER = ".\\data\\output\\";
	
	public static final int NUMBER_OF_IN_PARAMS = 4;
	public static final int TEST_METHOD_NAME_COLUMN = 0;
	public static final int TEST_ID_COLUMN = 1;
	
	public static final String EXCEL_DATA_SHEET_NAME = "Blad1";
	public static final String[] OUT_DATA_HEADERS = {"testCaseID", "result"};
	public static final String FILE_NAME_FORMAT = "test-results-%1$tY-%1$tm-%1$td-%1$tk-%1$tS.xlsx";
}
