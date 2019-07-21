package com.dzionsla.xlsxParser;

import java.io.File;
import java.util.ArrayList;

public class FileList {
	
	private ArrayList<File> fileList = new ArrayList<File>();
	private ArrayList<String> nameList = new ArrayList<String>();

	public ArrayList<File> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<File> fileList) {
		this.fileList = fileList;
	}
	
	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public void printFileList() {
		for (File file : fileList) {
			System.out.println(file.getAbsoluteFile());
		}
	}
	
	public void printNameList() {
		for (String name : nameList) {
			System.out.println(name);
		}
	}
	
	
	public String getFileExtension(File file) {
	    if (file == null) {
	        return "";
	    }
	    String name = file.getName();
	    int i = name.lastIndexOf('.');
	    String ext = i > 0 ? name.substring(i + 1) : "";
	    return ext;
	}
	
	public String getName(File file) {
	    if (file == null) {
	        return "";
	    }
	    String name = file.getName();
	    int i = name.lastIndexOf('.');
	    String ext = i > 0 ? name.substring(0, i) : "";
	    return ext;
	}

	public void fileListPath(File folder) {
		
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	fileListPath(fileEntry);
	        } else {
	        	if (getFileExtension(fileEntry).equals("xls")) {
	        		//System.out.println(getName(fileEntry));
	        		//System.out.println(fileEntry.getAbsolutePath());
	        		nameList.add(getName(fileEntry));
	        		fileList.add(fileEntry);
				}
	        }
	    }
	}
	
}
