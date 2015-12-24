package com.ixxus.preflight.Model;

public class File {
	private String name;
	private String isCorrect;
		
	public File(){
		
	}
	
	public File(String name, String isCorrect) {
		super();
		this.name = name;
		this.isCorrect = isCorrect;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
}
