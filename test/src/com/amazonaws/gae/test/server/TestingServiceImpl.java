package com.amazonaws.gae.test.server;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.gae.test.client.TestingService;
import com.amazonaws.gae.test.server.awsunit.AWSTestSuite;
import com.amazonaws.gae.test.shared.FieldVerifier;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TestingServiceImpl extends RemoteServiceServlet implements
		TestingService {

	public AWSTestResultSet runTests(String testSuiteName, String accessKey, String secretKey) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidAccessKey(accessKey)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		AWSTestSuite testSuite = AWSTestSuite.getTestSuite(testSuiteName);
		
		AWSTestResultSet resultSet = testSuite.runTests(credentials);
		
		return resultSet;
	}
	
	public AWSTestResult runTest(String testSuiteName, String testName, String accessKey, String secretKey) throws IllegalArgumentException {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSTestSuite testSuite = AWSTestSuite.getTestSuite(testSuiteName);
		return testSuite.runTest(testName, credentials);
	}

}
