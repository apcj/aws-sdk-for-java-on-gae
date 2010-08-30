package com.amazonaws.gae.test.client;

import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>TestingService</code>.
 */
public interface TestingServiceAsync {
	void runTests(String accessKey, String secretKey, AsyncCallback<AWSTestResultSet> callback)
			throws IllegalArgumentException;
}
