package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class PoiDemo {

	@Test
	public void write() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("mySheet");

		for (int row = 0; row < 10; row++) {
			HSSFRow rows = sheet.createRow(row);
			for (int col = 0; col < 10; col++) {
				rows.createCell(col).setCellValue("data" + row + col);
			}
		}

		File xlsFile = new File("poi.xls");
		FileOutputStream xlsStream = new FileOutputStream(xlsFile);
		workbook.write(xlsStream);
		workbook.close();
	}

	@Test
	public void write2() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet1 = workbook.createSheet("sheet1");
		HSSFSheet sheet2 = workbook.createSheet("sheet2");

		for (int row = 0; row < 10; row++) {
			HSSFRow rows1 = sheet1.createRow(row);
			HSSFRow rows2 = sheet2.createRow(row);
			for (int col = 0; col < 10; col++) {
				rows1.createCell(col).setCellValue("data" + row + col);
				rows2.createCell(col).setCellValue("2data" + row + col);
			}
		}

		File xlsFile = new File("poi.xls");
		FileOutputStream xlsStream = new FileOutputStream(xlsFile);
		workbook.write(xlsStream);
		workbook.close();
	}

//	public void read() {
//		File xlsFile = new File("poi.xls");
//		Workbook workbook = WorkbookFactory.create(xlsFile);
//		int sheetCount = workbook.getNumberOfSheets();
//		for (int i = 0; i < sheetCount; i++) {
//			Sheet sheet = workbook.getSheetAt(i);
//			int rows = sheet.getLastRowNum() + 1;
//			Row tmp = sheet.getRow(0);
//			if (tmp == null) {
//				continue;
//			}
//			int cols = tmp.getPhysicalNumberOfCells();
//			for (int row = 0; row < rows; row++) {
//				Row r = sheet.getRow(row);
//				for (int col = 0; col < cols; col++) {
//					System.out.printf("%10s", r.getCell(col).getStringCellValue());
//				}
//				System.out.println();
//			}
//		}
//	}

}
