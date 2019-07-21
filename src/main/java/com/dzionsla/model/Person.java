package com.dzionsla.model;

import java.util.ArrayList;

public class Person {
	private String fullName;
	private ArrayList<Project> projects;

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public ArrayList<Project> getProjects() {
		return projects;
	}
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

}
