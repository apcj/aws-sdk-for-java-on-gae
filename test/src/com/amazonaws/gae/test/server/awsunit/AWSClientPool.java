package com.amazonaws.gae.test.server.awsunit;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class AWSClientPool {
	private static Map<AWSCredentials, AWSClientPool> singletons = new HashMap<AWSCredentials, AWSClientPool>(); 
	
	private AmazonSimpleDB sdb;
	private AmazonSQS      sqs;
	
	public static AWSClientPool getClientPool(AWSCredentials credentials) {
		if (!singletons.containsKey(credentials)) {
			singletons.put(credentials, new AWSClientPool(credentials));
		}
		return singletons.get(credentials);
	}
	
	private AWSClientPool(AWSCredentials credentials) {
		sqs = new AmazonSQSClient(credentials);
		sdb = new AmazonSimpleDBClient(credentials);
	}
	
	public AmazonSimpleDB getAmazonSimpleDBClient() {
		return sdb;
	}
	
	public AmazonSQS getAmazonSQSClient() {
		return sqs;
	}
}
