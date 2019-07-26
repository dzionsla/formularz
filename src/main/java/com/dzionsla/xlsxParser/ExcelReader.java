package com.dzionsla.xlsxParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.dzionsla.model.Issue;
import com.dzionsla.model.Model;
import com.dzionsla.model.ModelDAO;
import com.dzionsla.model.Person;
import com.dzionsla.model.Project;

public class ExcelReader {
	private ArrayList<Person> persons = new ArrayList<Person>();
	

	//Nowak_Piotr.xls
	public ArrayList<Person> readXls(ArrayList<File> fileList, ModelDAO myModelDAO) throws FileNotFoundException, IOException{
		int rowCtr = 0;
		int cellCtr = 0;

	    for (File file : fileList) {
	    	ArrayList<Project> projects = new ArrayList<Project>();
	    	Person person = new Person();
	    	Model model = new Model();
	    	
	    	//System.out.println(file.getName());
	    	person.setFullName(file.getName());
	    	model.setPersonFullName(file.getName());
	    	FileInputStream fis = new FileInputStream(file);
	    	// we create an XSSF Workbook object for our XLSX Excel File
		    HSSFWorkbook workbook = new HSSFWorkbook(fis);
 
		    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
		    	ArrayList<Issue> issues = new ArrayList<Issue>();
		    	Project project = new Project();
				project.setName(workbook.getSheetName(i));
				model.setProjectName(workbook.getSheetName(i));
				
				HSSFSheet sheet = workbook.getSheetAt(i);

			    // we iterate on rows
			    Iterator<Row> rowIt = sheet.iterator();

			    while(rowIt.hasNext()) {
			    	Row row = rowIt.next();
			    	if (rowCtr != 0) {
			    		Issue issue = new Issue();

					    // iterate on cells for the current row
					    Iterator<Cell> cellIterator = row.cellIterator();
			
					    while (cellIterator.hasNext()) {
					        Cell cell = cellIterator.next();
					        if (cellCtr == 0) {
					        	issue.setDate(cell.getDateCellValue());
					        	model.setDate(cell.getDateCellValue());
					        	//System.out.print(issue.getDate());
							} else if (cellCtr == 1) {
								issue.setTask(cell.toString());
								model.setTask(cell.toString());
								//System.out.print(issue.getTask());
							} else if (cellCtr == 2) {
								issue.setHours(cell.getNumericCellValue());
								model.setHours(cell.getNumericCellValue());
								//System.out.print(issue.getHours());
							}		        
					        cellCtr++;
					        
					    }
					    myModelDAO.addFoodGroup(model);
					    
					    cellCtr = 0;
					    issues.add(issue);
					    //System.out.println();
					}
			    	//System.out.println(rowCtr);
			    	rowCtr++;
			    }
			    project.setIssues(issues);
			    
			    rowCtr=0;
			    projects.add(project);
			}
		    person.setProjects(projects);
		    
		    workbook.close();
		    fis.close();
		    persons.add(person);
		    
		    
		}
		return persons;
	}
	
}
