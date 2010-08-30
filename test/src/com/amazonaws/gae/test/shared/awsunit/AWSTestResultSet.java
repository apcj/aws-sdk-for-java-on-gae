package com.amazonaws.gae.test.shared.awsunit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;


public class AWSTestResultSet implements IsSerializable {
	private Map<String, AWSTestResult> resultSet = new HashMap<String, AWSTestResult>();
	
	public int getNumberOfTests() {
		return resultSet.size();
	}
	
	public void addTestResult(AWSTest test, AWSTestResult result) {
		resultSet.put(test.getName(), result);
	}
	
	public Set<String> getTests() {
		return resultSet.keySet();
	}
	
	public AWSTestResult getResult(String testName) {
		return resultSet.get(testName);
	}
}
