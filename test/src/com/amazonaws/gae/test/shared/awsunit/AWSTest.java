package com.amazonaws.gae.test.shared.awsunit;

import com.google.gwt.user.client.rpc.IsSerializable;


public abstract class AWSTest implements IsSerializable {
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract AWSTestResult execute();
}
