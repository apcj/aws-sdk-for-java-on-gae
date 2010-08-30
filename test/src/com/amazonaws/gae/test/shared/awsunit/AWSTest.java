package com.amazonaws.gae.test.shared.awsunit;

public abstract class AWSTest {
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract AWSTestResult execute();
}
