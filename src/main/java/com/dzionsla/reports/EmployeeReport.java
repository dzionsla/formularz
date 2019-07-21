package com.dzionsla.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dzionsla.model.Issue;
import com.dzionsla.model.Person;
import com.dzionsla.model.Project;

public class EmployeeReport {
	
	Map<String, Double> rep = new LinkedHashMap<String, Double>();
	
	public void clear() {
		rep.clear();
	}
	
	public void add(String name, double hours) {
		if (rep.containsKey(name)) {
			update(name, hours);
		} else {
			rep.put(name, hours);
		}
	}
		
	public void update(String name, double hours) {
		rep.put(name, rep.get(name) + hours);
	}	
	
// -------------------------------------------------- RAPORTS ------------------------------------------------
	// all reports
	
	// Dla podanego nazwiska tworzy raport z iloscia przepracowanych godzin na wszystkie projekt
	public void firstReport(ArrayList<Person> persons, String fullName) {
		double totalHours = 0;
		
		for (Person person : persons) {		
			if (person.getFullName().equals(fullName)) {
				//System.out.println(person.getFullName());
				for (Project project : person.getProjects()) {
					add(project.getName(), 0.0);
					for (Issue issue : project.getIssues()) {
						update(project.getName(), issue.getHours());
					}
					totalHours = 0;
				}
//				System.out.println(person.getFullName());
//				for (Project project : person.getProjects()) {
//					System.out.print(project.getName() + ": ");
//					for (Issue issue : project.getIssues()) {
//						totalHours += issue.getHours();
//					}
//					System.out.println(totalHours + " hours");
//					totalHours = 0;
//				}
			}
		}
		rep.forEach((key, value) -> System.out.println(key + ": " + value));	
	}
	
	//Dla podanego nazwiska i daty tworzy raport z iloscia przepracowanych godzin na wszystkie projekty
	public void secondReport(ArrayList<Person> persons, String fullName, Date startDate, Date endDate) {
		
		for (Person person : persons) {		
			if (person.getFullName().equals(fullName)) {
				for (Project project : person.getProjects()) {
					for (Issue issue : project.getIssues()) {
						if (issue.getDate().after(startDate) & issue.getDate().before(endDate)) {
							add(project.getName(), issue.getHours());
						}
					}
				}
			}
		}
		
		rep.forEach((key, value) -> System.out.println(key + ": " + value));
	}
	
	// Wypisuje wszystkie Nazwiska i ich projekty
	public void thirdReport(ArrayList<Person> persons) {
		List<Map> list = new ArrayList();
		for (Person person : persons) {	
			System.out.println(person.getFullName());
			for (Project project : person.getProjects()) {
				for (Issue issue : project.getIssues()) {
					add(project.getName(), issue.getHours());
				}
			}
			rep.forEach((key, value) -> System.out.println(key + ": " + value));
			rep.clear();
		}
		
	}
	// Wypisuje wszystkie projekty i wszystkie godziny na nie przeznaczone
	public void fourthReport(ArrayList<Person> persons) {
		for (Person person : persons) {	
			for (Project project : person.getProjects()) {
				for (Issue issue : project.getIssues()) {
					add(project.getName(), issue.getHours());
				}
			}
		}
		rep.forEach((key, value) -> System.out.println(key + ": " + value));
	}
	
	
	
	
}
