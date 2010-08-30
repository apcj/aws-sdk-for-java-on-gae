package com.amazonaws.gae.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>TestingService</code>.
 */
public interface TestingServiceAsync {
	void runTests(String accessKey, String secretKey, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
