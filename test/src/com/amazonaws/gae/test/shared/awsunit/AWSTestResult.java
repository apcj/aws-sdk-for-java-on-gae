package com.amazonaws.gae.test.shared.awsunit;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AWSTestResult implements IsSerializable {

	public static enum Code implements IsSerializable {
		SUCCESS,
		FAILURE
	}
	
	public Code code;
	public long time;
	
	public AWSTestResult() { }
	
	public AWSTestResult(Code code, long time) {
		this.code = code;
		this.time = time;
	}
}