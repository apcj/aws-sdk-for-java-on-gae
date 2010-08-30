package com.amazonaws.gae.test.client;

import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("run")
public interface TestingService extends RemoteService {
	AWSTestResultSet runTests(String testSuiteName, String accessKey, String secretKey) throws IllegalArgumentException;
	AWSTestResult    runTest(String testSuiteName, String testName, String accessKey, String secretKey) throws IllegalArgumentException;
}
