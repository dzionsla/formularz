package com.dzionsla.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dzionsla.model.Model;
import com.dzionsla.model.ModelDAO;
import com.dzionsla.model.Person;
import com.dzionsla.reports.EmployeeReport;
import com.dzionsla.reports.PdfReport;
import com.dzionsla.reports.XlsReport;
import com.dzionsla.xlsxParser.ExcelReader;
import com.dzionsla.xlsxParser.FileList;

public class App {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		FileList list = new FileList();
		
		File folder = new File("C:\\projects\\java\\Formularz\\src\\main\\resources");
		list.fileListPath(folder);
		
		ApplicationContext appContext = new FileSystemXmlApplicationContext("appContext.xml");
		ModelDAO myModelDAO = appContext.getBean("modelDAO", ModelDAO.class);
		myModelDAO.deleteTable("model");
		myModelDAO.createTable("model");
		//Model m = new Model(1, "2", "3", new Date(), "5", 6);
		//myModelDAO.addFoodGroup(m);
		
		
		ExcelReader read = new ExcelReader();
		ArrayList<Person> persons = read.readXls(list.getFileList(), myModelDAO);
		
		//System.out.println(myModelDAO.getModelById(10).toString());
		
		//myModelDAO.getModelsByPersonName("Duda_Olaf.xls");
		
		//myModelDAO.getModelsByPersonName("Marek_Wiecek.xls").forEach(System.out::println);
		
		String sDate1="2012-02-00";
		String sDate2="2013-02-00";   
	     
		myModelDAO.getModelsByProjectDate(sDate1, sDate2).forEach(System.out::println);
//		System.out.println("Raport numer 1:");
//		EmployeeReport report1 = new EmployeeReport();
//		report1.firstReport(persons, "Marek_Wiecek.xls");
		//report1.firstReport(persons, "Duda_Olaf.xls");
		//System.out.println();
//		System.out.println("Raport numer 2:");
//		String sDate1="01/01/2013";
//		String sDate2="01/01/2014";
//		SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
//		Date date1=formatter1.parse(sDate1);  
//		Date date2=formatter1.parse(sDate2); 
//		EmployeeReport report2 = new EmployeeReport();
//		report2.secondReport(persons, "Marek_Wiecek.xls", date1, date2);
		//System.out.println();
		//System.out.println("Raport numer 3:");
		//EmployeeReport report3 = new EmployeeReport();
		//report3.thirdReport(persons);
		//System.out.println();
//		System.out.println("Raport numer 4:");
//		EmployeeReport report4 = new EmployeeReport();
//		report4.fourthReport(persons);
		
		//XlsReport xlsReport = new XlsReport(3, report3.getRep());
		//XlsReport xlsReport = new XlsReport( report3.getList(),3);
		//PdfReport pdfReport = new PdfReport(4, report4.getRep());
		//PdfReport pdfReport = new PdfReport(report3.getList(),3); //nie zrobione jeszcze
		//mainMenu();
		
		//ApplicationContext appContext = new FileSystemXmlApplicationContext("appContext.xml");
		
		
		
		((FileSystemXmlApplicationContext) appContext).close();
		
	}
	
	static public void mainMenu() {
		printMainMenu();
		Scanner in = new Scanner(System.in);
		String input ;
		do {
			input = in.nextLine();
			
			switch (input) {
			case "1":
				System.out.println("1");
				break;
			case "2":
				System.out.println("2");
				break;
			case "3":
				System.out.println("3");
				break;
			case "q":
				System.out.println("Exit");
				break;
			default:
				System.out.println("Unknown");
				break;
			}
		} while (!input.equals("q"));

		
	}
	
	static public void printMainMenu() {
		System.out.println("Program do tworzenia formularzy.");
		System.out.println("Wcisnij odpowiedni przycisk:");
		System.out.println("1. Cos cos");
		System.out.println("2. Cos cos");
		System.out.println("3. Cos cos");
		System.out.println("4. Cos cos");
		System.out.println("q. Wyjscie");
		
	}

}
