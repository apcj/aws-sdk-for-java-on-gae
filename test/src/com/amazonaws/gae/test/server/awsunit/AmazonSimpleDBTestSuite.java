package com.amazonaws.gae.test.server.awsunit;

import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.DeleteDomainRequest;

public class AmazonSimpleDBTestSuite extends AWSTestSuite {
	private AmazonSimpleDB sdb;
	protected static AmazonSimpleDBTestSuite singleton;
	
	public static AmazonSimpleDBTestSuite getInstance() {
		if (singleton == null)
			singleton = new AmazonSimpleDBTestSuite();
		return singleton;
	}
	
	private AmazonSimpleDBTestSuite() {
		super();
		name = "Amazon SimpleDB";
		
		ListDomainsTest listDomainsTest = new ListDomainsTest();
		CreateDomainTest createDomainTest = new CreateDomainTest();
		DeleteDomainTest deleteDomainTest = new DeleteDomainTest();
		
		tests.put(listDomainsTest.getName(), listDomainsTest);
		tests.put(createDomainTest.getName(), createDomainTest);
		tests.put(deleteDomainTest.getName(), deleteDomainTest);
		
		singleton = this;
	}
	
	@Override
	void setUp() {
		if (sdb == null) sdb = clientPool.getAmazonSimpleDBClient();
	}

	class ListDomainsTest extends AWSTest {
		public ListDomainsTest() {
			name = "List Domains";
		}
		
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
	
	class CreateDomainTest extends AWSTest {
		public CreateDomainTest() {
			name = "Create Domains";
		}
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			String testDomainName = "TestDomain84328";
			sdb.createDomain(new CreateDomainRequest().withDomainName(testDomainName));
			long endTime = System.currentTimeMillis();
			return new AWSTestResult(code, endTime - startTime);
		}
	}
	
	class DeleteDomainTest extends AWSTest {
		public DeleteDomainTest() {
			name = "Delete Domains";
		}
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			String testDomainName = "TestDomain84328";
			sdb.deleteDomain(new DeleteDomainRequest().withDomainName(testDomainName));
			long endTime = System.currentTimeMillis();
			return new AWSTestResult(code, endTime - startTime);
		}
	}
}
