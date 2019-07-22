package com.dzionsla.reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsReport {

	HSSFWorkbook workbook; 
	HSSFSheet sheet;
	HSSFCell cell;
	HSSFRow row;
	
	public XlsReport(int iReport, Map<String, Double> report) throws IOException {
		createXls(iReport);
		writeToExcel(report);
		saveExcel("Report " + iReport);
	}
	
	public XlsReport(Map<String, Map<String, Double>> report, int iReport) throws IOException {
		createXls(iReport);
		writeToExcel2(report);
		saveExcel("Report " + iReport);
	}
	
	private void createXls(int iReport) {
		workbook = new HSSFWorkbook(); 
		sheet = workbook.createSheet("Report " + iReport);
	}
	
	private void writeToExcel(Map<String, Double> report) {
		writeHeadToExcel();
		int ctrRow = 1;
		int ctrCol = 0;
		for (Map.Entry<String, Double> rep : report.entrySet()) {		
			row = sheet.createRow(ctrRow);
			cell = row.createCell(0);
			cell.setCellValue(rep.getKey());
			cell = row.createCell(1);
			cell.setCellValue(rep.getValue());
			ctrRow++;
		}			
	}
	
	private void writeToExcel2(Map<String, Map<String, Double>> list) {
		writeHeadToExcel();
		int ctrRow = 1;
		int ctrCol = 0;
		for (Map.Entry<String, Map<String, Double>> ls : list.entrySet()) {		
			row = sheet.createRow(ctrRow++);
			cell = row.createCell(0);
			cell.setCellValue(ls.getKey());

			Map<String, Double> test = ls.getValue();
			
			for (Map.Entry<String, Double> rep : test.entrySet()) {
				row = sheet.createRow(ctrRow++);
				cell = row.createCell(0);
				cell.setCellValue(rep.getKey());
				cell = row.createCell(1);
				cell.setCellValue(rep.getValue());
			}
			
		}			
	}
	
	private void writeHeadToExcel() {
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Project name");
		cell = row.createCell(1);
		cell.setCellValue("Hours");
	}
	
	private void saveExcel(String fileName) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName + ".xls");
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
	}
	
	
}
