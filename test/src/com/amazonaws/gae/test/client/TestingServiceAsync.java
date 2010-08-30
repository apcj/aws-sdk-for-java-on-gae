package com.amazonaws.gae.test.client;

import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>TestingService</code>.
 */
public interface TestingServiceAsync {
	void runTests(String testSuiteName, String accessKey, String secretKey, AsyncCallback<AWSTestResultSet> callback)
			throws IllegalArgumentException;
	
	void runTest(String testSuiteName, String testName, String accessKey, String secretKey,
			AsyncCallback<AWSTestResult> callback) throws IllegalArgumentException;
}
