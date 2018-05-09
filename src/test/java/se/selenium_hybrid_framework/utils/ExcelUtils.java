package se.selenium_hybrid_framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet excelWorksheet;
	private static XSSFWorkbook excelWorkbook;
	private static String outputPath = "";

	public static void setExcelInputFile(String path, String sheetName) {
		try {
			FileInputStream excelFile = new FileInputStream(path);
			excelWorkbook = new XSSFWorkbook(excelFile);
			excelWorksheet = excelWorkbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("Error reading excel file");
		}
	}

	public static void createExcelOutputFile(String path, String sheetName) {

		outputPath = path;
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			workbook.createSheet(sheetName);
			
			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			
		} catch (Exception e) {
			System.out.println("Error creating excel file");
		}
	}

	public static String getCellData(int rowNum, int colNum) {
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(excelWorksheet.getRow(rowNum).getCell(colNum));
	}

	public static List<Integer> getRowsContaining(String text, int col) {
		int rowCount = excelWorksheet.getLastRowNum();

		List<Integer> rowsContaining = new ArrayList<Integer>();
		for (int i = 0; i <= rowCount; i++) {
			if (getCellData(i, col).equalsIgnoreCase(text)) {
				rowsContaining.add(i);
			}
		}
		return rowsContaining;
	}

	//Get data from rows containing a certain testCaseName
	public static Object[][] getTestData(String testCaseName, int columns) {
		List<Integer> rows = getRowsContaining(testCaseName, Constant.TEST_METHOD_NAME_COLUMN);
		Object[][] data = new Object[rows.size()][columns];

		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < columns; j++) {
				data[i][j] = getCellData(i, j);
			}
		}
		return data;
	}

	//Write data to specific cell in output file
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			FileInputStream file = new FileInputStream(outputPath);

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;

			// Retrieve the row and check for null
			XSSFRow sheetrow = sheet.getRow(RowNum);
			if (sheetrow == null) {
				sheetrow = sheet.createRow(RowNum);
			}
			// Update the value of cell
			cell = sheetrow.getCell(ColNum);
			if (cell == null) {
				cell = sheetrow.createCell(ColNum);
			}
			cell.setCellValue(Result);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(outputPath));
			workbook.write(outFile);
			workbook.close();
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Clear a sheet in output file
	public static void clearSheet(String sheetName) {
		try {
			FileInputStream file = new FileInputStream(outputPath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
			workbook.createSheet();
			workbook.setSheetName(0, sheetName);
			workbook.close();
			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(outputPath));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Write a complete row in output-file
	public static void writeRowToOutputFile(String[] rowData){
		try {
			FileInputStream file = new FileInputStream(outputPath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			file.close();
			
			XSSFRow sheetrow;
			Cell cell = null;
			
			if(sheet.getRow(0) == null) {
				sheetrow = sheet.createRow(0);
			}
			else {
				sheetrow = sheet.createRow(sheet.getLastRowNum()+1);
			}
			
			for(int i=0; i<rowData.length; i++){
				// Update the value of cell
				cell = sheetrow.getCell(i);
				if (cell == null) {
					cell = sheetrow.createCell(i);
				}
				cell.setCellValue(rowData[i]);
			}

			FileOutputStream outFile = new FileOutputStream(new File(outputPath));
			workbook.write(outFile);
			workbook.close();
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
