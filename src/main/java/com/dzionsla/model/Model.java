package com.dzionsla.model;

import java.util.Date;

public class Model {
	private int id;
	private String personFullName;
	private String projectName;
	private Date date;
	private String task;
	private double hours;

	public Model() {
		
	}
	
	public Model(int id, String personFullName, String projectName, Date date, String task, double hours) {
		this.id = id;
		this.personFullName = personFullName;
		this.projectName = projectName;
		this.date = date;
		this.task = task;
		this.hours = hours;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonFullName() {
		return personFullName;
	}
	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		return "Model [id=" + id + ", personFullName=" + personFullName + ", projectName=" + projectName + ", date="
				+ date + ", task=" + task + ", hours=" + hours + "]";
	}
	
}
