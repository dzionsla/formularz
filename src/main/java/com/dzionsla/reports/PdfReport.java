package com.dzionsla.reports;

import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class PdfReport {

	PDDocument doc;
	PDPage page;
	PDPageContentStream content;
	
	public PdfReport(int iReport, Map<String, Double> report) throws IOException {
		createPDF(iReport);
		writeToPDF(report);
		savePDF("Report" + iReport);
	}
	
	public PdfReport(Map<String, Map<String, Double>> report, int iReport) throws IOException {
		createPDF(iReport);
		writeToPDF2(report);
		savePDF("Report" + iReport);
	}

	private void createPDF(int iReport) {
		doc = new PDDocument();
		page = new PDPage();
		doc.addPage(page);
	}
	
	private void writeToPDF(Map<String, Double> report) throws IOException {
		content = new PDPageContentStream(doc, page);
		writeHeadToPDF(content);
		content.setFont(PDType1Font.TIMES_ROMAN, 16);
		for (Map.Entry<String, Double> rep : report.entrySet()) {	
			content.newLine(); 
			content.showText(rep.getKey() + "           " +rep.getValue());
		}	
		
		content.endText();
		content.close();
	}
	
	private void writeToPDF2(Map<String, Map<String, Double>> report) throws IOException {
		content = new PDPageContentStream(doc, page);
		writeHeadToPDF(content);
		content.setFont(PDType1Font.TIMES_ROMAN, 16);
		
		for (Map.Entry<String, Map<String, Double>> ls : report.entrySet()) {		
			content.newLine(); 
			content.showText(ls.getKey());
			
			Map<String, Double> test = ls.getValue();
			
			for (Map.Entry<String, Double> rep : test.entrySet()) {
				content.newLine(); 
				content.showText(rep.getKey() + "           " +rep.getValue());
			}	
		}		
		content.endText();
		content.close();	
	}
	
	private void writeHeadToPDF(PDPageContentStream content) throws IOException {	
		content.beginText();
		
		content.setFont(PDType1Font.TIMES_BOLD_ITALIC, 16);
		content.setLeading(20.5f);
		content.newLineAtOffset(25, 750);
		String test = "Project name    Hours";
		content.showText(test);
		//content.newLine();
	}
	
	private void savePDF(String fileName) throws IOException {
		doc.save(fileName + ".pdf");
		doc.close();
	}
}
