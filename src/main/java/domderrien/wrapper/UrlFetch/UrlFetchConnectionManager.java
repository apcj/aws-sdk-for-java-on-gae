/******************************************************************************* 
 *  Adaptation for the Amazon FPS library to work on the Java platform of
 *  Google App Engine.
 *  
 *  Copyright 2010 Dom Derrien
 *  Licensed under the Apache License, Version 2.0
 */

package domderrien.wrapper.UrlFetch;

import org.apache.commons.httpclient.ConnectionPoolTimeoutException;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class UrlFetchConnectionManager implements HttpConnectionManager {

	private HttpConnectionManagerParams params;
	private HttpConnection connection;
	
	public void closeIdleConnections(long timeout) {
		throw new RuntimeException("closeIdleConnections(long)");
	}

	public HttpConnection getConnection(HostConfiguration hostConfiguration) {
		throw new RuntimeException("getConnection(HostConfiguration)");
		// return null;
	}

	public HttpConnection getConnection(HostConfiguration hostConfiguration, long timeout) throws HttpException {
		throw new RuntimeException("getConnection(HostConfiguration, long)");
		// return null;
	}

	public HttpConnection getConnectionWithTimeout(HostConfiguration hostConfiguration, long timeout) throws ConnectionPoolTimeoutException {
		// As reported in http://code.google.com/appengine/docs/java/urlfetch/usingjavanet.html#Java_Net_Features_Not_Supported
		// > The app cannot set explicit connection timeouts for the request.
		if (connection != null) {
			releaseConnection(connection);
		}
		connection = new UrlFetchHttpConnection(hostConfiguration);
		return connection;
	}

	public HttpConnectionManagerParams getParams() {
		return params;
	}

	public void releaseConnection(HttpConnection connection) {
		connection.releaseConnection();
	}

	public void setParams(HttpConnectionManagerParams params) {
		// Parameters set in AmazonFPSClient#configureHttpClient:
        // - ConnectionTimeout: 50000 ms
		// - SoTimeout: 50000 ms
		// - StaleCheckingEnabled: true
		// - TcpNoDelay: true
		// - MaxTotalConnections: 100 (as proposed in the default config.properties file)
		// - MaxConnectionsPerHost: 100 (as proposed in the default config.properties file)

		this.params = params;
	}
	
}
