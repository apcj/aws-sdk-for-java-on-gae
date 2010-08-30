package com.amazonaws.gae.test.server.awsunit;

import java.io.Serializable;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.services.simpledb.AmazonSimpleDB;

public class AmazonSimpleDBTestSuite extends AWSTestSuite {
	AmazonSimpleDB sdb;
	
	public AmazonSimpleDBTestSuite(AWSCredentials credentials) {
		super(credentials);
		sdb = clientPool.getAmazonSimpleDBClient();
		tests.add(new ListDomainsTest());
	}

	class ListDomainsTest extends AWSTest implements Serializable {
		private static final long serialVersionUID = 3193332643485664040L;
		protected String name = "List Domains Test";
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			int numDomains = sdb.listDomains().getDomainNames().size();
			if (numDomains < 0) code = AWSTestResult.Code.FAILURE;
			long endTime = System.currentTimeMillis();
			
			return new AWSTestResult(code, endTime - startTime);
		}
		
	}
}
