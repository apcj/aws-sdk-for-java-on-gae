package com.amazonaws.gae.test.server;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.gae.test.client.TestingService;
import com.amazonaws.gae.test.shared.FieldVerifier;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.ListDomainsRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TestingServiceImpl extends RemoteServiceServlet implements
		TestingService {

	public String runTests(String accessKey, String secretKey) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidAccessKey(accessKey)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		
		AmazonSimpleDB sdb = new AmazonSimpleDBClient(new BasicAWSCredentials(accessKey, secretKey));
		

		return "You have " + sdb.listDomains(new ListDomainsRequest()).getDomainNames().size() + " SDB domains";
	}

}
