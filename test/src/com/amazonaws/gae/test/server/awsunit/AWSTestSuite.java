package com.amazonaws.gae.test.server.awsunit;

import java.util.LinkedList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;

public abstract class AWSTestSuite {

	protected List<AWSTest> tests;
	protected AWSClientPool clientPool;
	
	public AWSTestSuite(AWSCredentials credentials) {
		clientPool = AWSClientPool.getClientPool(credentials);
		tests = new LinkedList<AWSTest>();
	}
	
	public AWSTestResultSet runTests() {
		AWSTestResultSet resultSet = new AWSTestResultSet();
		for (AWSTest test : tests) {
			resultSet.addTestResult(test, test.execute());
		}
		
		return resultSet;
	}
	
}
