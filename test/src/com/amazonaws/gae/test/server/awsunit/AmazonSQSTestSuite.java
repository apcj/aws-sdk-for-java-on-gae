package com.amazonaws.gae.test.server.awsunit;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.gae.test.shared.awsunit.AWSTest;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;

public class AmazonSQSTestSuite extends AWSTestSuite {
	private AmazonSQS sqs;
	protected static AmazonSQSTestSuite singleton;
	
	public static AmazonSQSTestSuite getInstance() {
		if (singleton == null)
			singleton = new AmazonSQSTestSuite();
		return singleton;
	}
	
	
	private static final String testQueueName = "TestQueue84328";
	private String testQueueUrl;
	
	private AmazonSQSTestSuite() {
		super();
		name = "Amazon SQS";
		
		CreateQueueTest createQueueTest = new CreateQueueTest();
		ListQueuesTest listQueuesTest = new ListQueuesTest();
		DeleteQueueTest deleteQueueTest = new DeleteQueueTest();
		
		tests.add(createQueueTest);
		tests.add(listQueuesTest);
		tests.add(deleteQueueTest);

		singleton = this;
	}
	
	@Override
	void setUp() {
		if (sqs == null) sqs = clientPool.getAmazonSQSClient();
	}
	
	class CreateQueueTest extends AWSTest {
		public CreateQueueTest() {
			name = "Create Queue";
		}
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			CreateQueueResult r = sqs.createQueue(
					new CreateQueueRequest().withQueueName(testQueueName));
			if (r == null || r.getQueueUrl().length() < testQueueName.length())
				code = AWSTestResult.Code.FAILURE;
			testQueueUrl = r.getQueueUrl();
			long endTime = System.currentTimeMillis();
			return new AWSTestResult(code, endTime - startTime);
		}
	}
	
	class ListQueuesTest extends AWSTest {
		public ListQueuesTest() {
			name = "List Queues";
		}
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			ListQueuesResult r = sqs.listQueues();
			if (!r.getQueueUrls().contains(testQueueUrl))
				code = AWSTestResult.Code.FAILURE;
			long endTime = System.currentTimeMillis();
			
			return new AWSTestResult(code, endTime - startTime);
		}
	}
	
	class DeleteQueueTest extends AWSTest {
		public DeleteQueueTest() {
			name = "Delete Queue";
		}
		
		@Override
		public AWSTestResult execute() {
			AWSTestResult.Code code = AWSTestResult.Code.SUCCESS;
			
			long startTime = System.currentTimeMillis();
			try {
				sqs.deleteQueue(new DeleteQueueRequest().withQueueUrl(testQueueUrl));
			} catch (AmazonServiceException ase) {
				code = AWSTestResult.Code.FAILURE;
			}
			long endTime = System.currentTimeMillis();
			
			return new AWSTestResult(code, endTime - startTime);
		}
	}

}
