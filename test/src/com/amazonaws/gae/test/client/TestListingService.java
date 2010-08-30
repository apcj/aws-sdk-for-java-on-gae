package com.amazonaws.gae.test.client;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("list")
public interface TestListingService extends RemoteService {
	Set<String> listTestSuites();
	List<String> listTests(String testSuiteName);
}
