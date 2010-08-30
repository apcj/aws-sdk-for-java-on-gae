package com.amazonaws.gae.test.shared;

public class FieldVerifier {

	public static boolean isValidAccessKey(String accessKey) {
		if (accessKey == null) {
			return false;
		}
		return accessKey.length() > 3;
	}
}
