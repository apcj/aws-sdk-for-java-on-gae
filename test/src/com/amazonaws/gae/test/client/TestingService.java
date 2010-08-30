package com.amazonaws.gae.test.client;

import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface TestingService extends RemoteService {
	AWSTestResultSet runTests(String accessKey, String secretKey) throws IllegalArgumentException;
}
