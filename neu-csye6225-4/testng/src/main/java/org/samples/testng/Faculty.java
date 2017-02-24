package org.samples.testng;

public class Faculty implements IPerson {

	private String fullName;

	public Faculty(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

}
