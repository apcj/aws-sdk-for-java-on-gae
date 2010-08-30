package com.amazonaws.gae.test.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.amazonaws.gae.test.client.TestListingService;
import com.amazonaws.gae.test.server.awsunit.AWSTestSuite;
import com.amazonaws.gae.test.server.awsunit.AmazonSimpleDBTestSuite;
import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TestListingServiceImpl extends RemoteServiceServlet implements TestListingService {

	@Override
	public List<String> listTests(String testSuiteName) {
		List<String> listNames = new LinkedList<String>();
		for (AWSTest test : AmazonSimpleDBTestSuite.getInstance().getTests()) {
			listNames.add(test.getName());
		}
		
		return listNames;
	}
	
	@Override
	public Set<String> listTestSuites() {
		return AWSTestSuite.getTestSuiteNames();
	}

}
