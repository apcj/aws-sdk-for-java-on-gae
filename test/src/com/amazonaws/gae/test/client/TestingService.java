package com.amazonaws.gae.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface TestingService extends RemoteService {
	String runTests(String accessKey, String secretKey) throws IllegalArgumentException;
}
