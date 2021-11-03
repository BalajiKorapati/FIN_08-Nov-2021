package com.oracle.acs.util;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class ExcelOperations {

	XSSFWorkbook wb;

	/**
	 * Pass xlsx path as parameter
	 * @param path : example './Databank/input.xlsx'
	 * @throws IOException 
	 */
	public ExcelOperations(String path) throws IOException {
		// TODO Auto-generated constructor stub
		FileInputStream fis = new FileInputStream(path);
		wb = new XSSFWorkbook (fis);
		System.out.println("Databank "+path+" loaded successfully");
	}
	
	public int getRowCount(String sheetName){
		
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	public static void main(String [] args) throws IOException{}

	
	
	/**
	 * Returns value of a cell
	 * @param row : Row start with 1 wherefirst row is column name, so second row is considered as 1
	 * @param column : First row considered as column name
	 * @return
	 */
	public String read(String sheet, int row, String column){
		XSSFSheet wbSheet = wb.getSheet(sheet);
		Row colNames = wbSheet.getRow(0);
		int colCount =0;
		boolean found = false;
		for( colCount =0 ; colCount<colNames.getLastCellNum(); colCount++){
			if(colNames.getCell(colCount).getStringCellValue().toLowerCase().trim().equals(column.toLowerCase())){
				found = true;
				break;
			}
		}
		if(found){
			System.out.println("Column found. Coulmn number :"+(colCount+1));

		}else{
			throw new RuntimeException("Column "+column+" not found in excel. Sheet : "+wbSheet.getSheetName());
		}
		String value = "";
		
		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(wb);
		objFormulaEvaluator.evaluate(wbSheet.getRow(row).getCell(colCount));
	    value = objDefaultFormat.formatCellValue(wbSheet.getRow(row).getCell(colCount),objFormulaEvaluator);
		

		return value;
	}
}
