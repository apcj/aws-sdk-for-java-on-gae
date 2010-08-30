package com.amazonaws.gae.test.client;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TestListingServiceAsync {
	void listTestSuites(AsyncCallback<Set<String>> callback);
	void listTests(String testSuiteName, AsyncCallback<List<String>> callback);
}