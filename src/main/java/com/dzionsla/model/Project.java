package com.dzionsla.model;

import java.util.ArrayList;

public class Project {
	private String name;
	private ArrayList<Issue> issues;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Issue> getIssues() {
		return issues;
	}
	public void setIssues(ArrayList<Issue> issues) {
		this.issues = issues;
	}
	
	
}
