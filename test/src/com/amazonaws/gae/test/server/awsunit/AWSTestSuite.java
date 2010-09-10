package com.amazonaws.gae.test.server.awsunit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;

public abstract class AWSTestSuite {
	protected List<AWSTest> tests;
	protected AWSClientPool clientPool;
	
	protected String name;
	
	@SuppressWarnings("serial")
	private static final Map<String, AWSTestSuite> singletons = new HashMap<String, AWSTestSuite>() {{
		put(AmazonSimpleDBTestSuite.getInstance().getName(), AmazonSimpleDBTestSuite.getInstance());
		put(AmazonSQSTestSuite.getInstance().getName(), AmazonSQSTestSuite.getInstance());
	}};
	
	public static AWSTestSuite getTestSuite(String testSuiteName) {
		return singletons.get(testSuiteName);
	}
	
	public static Set<String> getTestSuiteNames() {
		return new HashSet<String>(singletons.keySet());
	}
	
	protected AWSTestSuite() {
		tests = new LinkedList<AWSTest>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<AWSTest> getTests() {
		return tests;
	}
	
	abstract void setUp();
	
	public AWSTestResultSet runTests(AWSCredentials credentials) {
		clientPool = AWSClientPool.getClientPool(credentials);
		setUp();
		
		AWSTestResultSet resultSet = new AWSTestResultSet(name);
		for (AWSTest test : tests) {
			resultSet.addTestResult(test, test.execute());
		}
		
		return resultSet;
	}
	
	public AWSTestResult runTest(String testName, AWSCredentials credentials) {
		clientPool = AWSClientPool.getClientPool(credentials);
		setUp();
		
		return tests.get(tests.indexOf(testName)).execute();
	}
	
}
