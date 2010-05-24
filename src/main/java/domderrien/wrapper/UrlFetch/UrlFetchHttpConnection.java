/******************************************************************************* 
 *  Adaptation for the Amazon FPS library to work on the Java platform of
 *  Google App Engine.
 *  
 *  Copyright 2010 Dom Derrien
 *  Licensed under the Apache License, Version 2.0
 */

package domderrien.wrapper.UrlFetch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;

import javamocks.io.MockInputStream;
import javamocks.io.MockOutputStream;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class UrlFetchHttpConnection extends HttpConnection {

	private static URLFetchService urlFS = URLFetchServiceFactory.getURLFetchService();

	private HostConfiguration hostConfiguration;
	private HTTPRequest _request;
	private HTTPResponse _response;
	private MockOutputStream _requestBody = new MockOutputStream();
	private MockInputStream _responseBody = new MockInputStream();

	
	private HTTPRequest getRequest() throws MalformedURLException {
		if (_request == null) {
			_request = new HTTPRequest(
					new URL(hostConfiguration.getHostURL()), 
					HTTPMethod.POST, // AmazonFPSClient#invoke(Class<T>, Map<String,String>) uses only POST method
					FetchOptions.Builder.disallowTruncate().followRedirects()
			);
		}
		return _request;
	}
	
	private static final String SEPARATOR = ": ";
	private static final int SEPARATOR_LENGTH = SEPARATOR.length();
	private static final String NEW_LINE = "\r\n";

	private HTTPResponse getResponse() throws MalformedURLException, IOException {
		if (_response == null) {
			System.out.println("_response == null");
			// Get the response from the remote service
			_response = urlFS.fetch(getRequest());
			// Rebuild stream of HTTP headers (except the HTTP status retrieved from readLine(String) method)
			StringBuilder buffer = new StringBuilder();
			for (HTTPHeader header: _response.getHeaders()) {
				buffer.append(header.getName()).append(SEPARATOR).append(header.getValue()).append(NEW_LINE);
			}
			buffer.append(NEW_LINE);
			// Rebuild stream of HTTP content (chunked-encoded)
			buffer.append(Integer.toString(_response.getContent().length, 16)).append(NEW_LINE);
			buffer.append(new String(_response.getContent())).append(NEW_LINE);
			buffer.append("0").append(NEW_LINE);
			_responseBody.resetActualContent(buffer.toString());
			System.out.println(buffer.toString());
			System.out.println("=====");
			System.out.println(_responseBody);
		}
		System.out.println("Returning _response");
		System.out.println(_responseBody);
		return _response;
	}

	/**
	 * Default constructor
	 * @param hostConfiguration
	 */
	public UrlFetchHttpConnection(HostConfiguration hostConfiguration) {
		super(hostConfiguration);
		this.hostConfiguration = hostConfiguration;
	}

	@Override
	protected void assertNotOpen() throws IllegalStateException {
		throw new RuntimeException("assertNotOpen()");
	}

	@Override
	protected void assertOpen() throws IllegalStateException {
		assert(_response != null);
	}

	@Override
	public void close() {
		// Nothing to do!
	}

	@Override
	public boolean closeIfStale() throws IOException {
		// Safe call, passed to the inherited method
		return super.closeIfStale();
	}

	@Override
	protected void closeSocketAndStreams() {
		throw new RuntimeException("closeSocketAndStreams()");
	}

	@Override
	public void flushRequestOutputStream() throws IOException {
		getRequest().setPayload(_requestBody.getStream().toString().getBytes());
	}

	@Override
	public String getHost() {
		return hostConfiguration.getHost();
	}

	@Override
	public HttpConnectionManager getHttpConnectionManager() {
		throw new RuntimeException("getHttpConnectionManager()");
	}

	@Override
	public InputStream getLastResponseInputStream() {
		throw new RuntimeException("getLastResponseInputStream()");
	}

	@Override
	public InetAddress getLocalAddress() {
		throw new RuntimeException("getLocalAddress()");
	}

	@Override
	public HttpConnectionParams getParams() {
		return new HttpConnectionParams();
	}

	@Override
	public int getPort() {
		return hostConfiguration.getPort();
	}

	@Override
	public Protocol getProtocol() {
		return hostConfiguration.getProtocol();
	}

	@Override
	public String getProxyHost() {
		throw new RuntimeException("getProxyHost()");
	}

	@Override
	public int getProxyPort() {
		throw new RuntimeException("getProxyPort()");
	}

	@Override
	public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
		return _requestBody;
	}

	@Override
	public InputStream getResponseInputStream() throws IOException {
		return _responseBody;
	}

	@Override
	public int getSendBufferSize() throws SocketException {
		throw new RuntimeException("getSendBufferSize()");
	}

	@Override
	protected Socket getSocket() {
		throw new RuntimeException("getSocket()");
	}

	@Override
	public int getSoTimeout() throws SocketException {
		throw new RuntimeException("getSoTimeout()");
	}

	@Override
	public String getVirtualHost() {
		throw new RuntimeException("getVirtualHost()");
	}

	@Override
	protected boolean isLocked() {
		throw new RuntimeException("isLocked()");
	}

	@Override
	public boolean isOpen() {
		// Safe call, passed to inherited method
		return super.isOpen();
	}

	@Override
	public boolean isProxied() {
		// Safe call, passed to inherited method
		return super.isProxied();
	}

	@Override
	public boolean isResponseAvailable() throws IOException {
		return _response != null;
	}

	@Override
	public boolean isResponseAvailable(int timeout) throws IOException {
		return _response != null;
	}

	@Override
	public boolean isSecure() {
		return hostConfiguration.getPort() == 443;
	}

	@Override
	protected boolean isStale() throws IOException {
		throw new RuntimeException("isStale()");
	}

	@Override
	public boolean isStaleCheckingEnabled() {
		throw new RuntimeException("isStaleCheckingEnabled()");
	}

	@Override
	public boolean isTransparent() {
		// Safe call, passed to the inherited method
		return super.isTransparent();
	}
	
	@Override
	public void open() throws IOException {
		// Nothing to do
	}

	@Override
	public void print(String data, String charset) throws IOException, IllegalStateException {
		// Save the passed HTTP headers for the request
		int idx = data.indexOf(SEPARATOR);
		if (idx != -1) {
			String name = data.substring(0, idx);
			String value = data.substring(idx + SEPARATOR_LENGTH).trim();
			getRequest().addHeader(new HTTPHeader(name, value));
		}
		// Other information are just ignored safely 
	}

	@Override
	public void print(String data) throws IOException, IllegalStateException {
		throw new RuntimeException("print(string): " + data);
	}

	@Override
	public void printLine() throws IOException, IllegalStateException {
		throw new RuntimeException("printLine()");
	}

	@Override
	public void printLine(String data, String charset) throws IOException, IllegalStateException {
		throw new RuntimeException("printLine(string, String): " + data + " -- " + charset);
	}

	@Override
	public void printLine(String data) throws IOException, IllegalStateException {
		throw new RuntimeException("printLine(string): " + data);
	}

	@Override
	public String readLine() throws IOException, IllegalStateException {
		throw new RuntimeException("readLine()");
	}

	private boolean waitForHttpStatus = true;
	
	@Override
	public String readLine(String charset) throws IOException, IllegalStateException {
		if (waitForHttpStatus) {
			// Dom Derrien: called only once to get the HTTP status, other information being read from the response output stream
			int responseCode = getResponse().getResponseCode();
			String line = "HTTP/1.1 " + responseCode;
			switch(responseCode) {
				case HttpStatus.SC_OK: line += " OK"; break;
				case HttpStatus.SC_BAD_REQUEST: line += " BAD REQUEST"; break;
				case HttpStatus.SC_UNAUTHORIZED: line += " UNAUTHORIZED"; break;
				case HttpStatus.SC_FORBIDDEN: line += " FORBIDDEN"; break;
				case HttpStatus.SC_NOT_FOUND: line += " NOT FOUND"; break;
				case HttpStatus.SC_INTERNAL_SERVER_ERROR: line += " INTERNAL SERVER ERROR"; break;
				case HttpStatus.SC_SERVICE_UNAVAILABLE: line += " SERVICE UNAVAILABLE"; break;
				default: line = "HTTP/1.1 " + HttpStatus.SC_BAD_REQUEST + " BAD REQUEST";
			}
			waitForHttpStatus = false;
			return line;
		}
		throw new RuntimeException("readLine(String)");
	}

	@Override
	public void releaseConnection() {
		// Do nothing, connection closed automatically...
	}

	@Override
	public void setConnectionTimeout(int timeout) {
		throw new RuntimeException("setConnectionTimeout(int)");
	}

	@Override
	public void setHost(String host) throws IllegalStateException {
		throw new RuntimeException("setHost(String");
	}

	@Override
	public void setHttpConnectionManager(HttpConnectionManager httpConnectionManager) {
		throw new RuntimeException("setHttpConnectionManager(HttpConnectionManager");
	}

	@Override
	public void setLastResponseInputStream(InputStream inStream) {
		// Safe call, passed to inherited method
		super.setLastResponseInputStream(inStream);
	}

	@Override
	public void setLocalAddress(InetAddress localAddress) {
		throw new RuntimeException("setLocalAddress(InetAddress)");
	}

	@Override
	protected void setLocked(boolean locked) {
		// Safe call, passed to inherited method
		super.setLocked(locked);
	}

	@Override
	public void setParams(HttpConnectionParams params) {
		throw new RuntimeException("setParams(HttpConnectionParams)");
	}

	@Override
	public void setPort(int port) throws IllegalStateException {
		throw new RuntimeException("setPort(int)");
	}

	@Override
	public void setProtocol(Protocol protocol) {
		throw new RuntimeException("setProtocol(Protocol)");
	}

	@Override
	public void setProxyHost(String host) throws IllegalStateException {
		throw new RuntimeException("setProxyHost(String)");
	}

	@Override
	public void setProxyPort(int port) throws IllegalStateException {
		throw new RuntimeException("setProxyPort(int)");
	}

	@Override
	public void setSendBufferSize(int sendBufferSize) throws SocketException {
		throw new RuntimeException("setSendBufferSize(int)");
	}

	@Override
	public void setSocketTimeout(int timeout) throws SocketException, IllegalStateException {
		// Safe call, passed to inherited method
		super.setSocketTimeout(timeout);
	}

	@Override
	public void setSoTimeout(int timeout) throws SocketException, IllegalStateException {
		throw new RuntimeException("setSoTimeout(int)");
	}

	@Override
	public void setStaleCheckingEnabled(boolean staleCheckEnabled) {
		throw new RuntimeException("setStaleCheckingEnabled(boolean)");
	}

	@Override
	public void setVirtualHost(String host) throws IllegalStateException {
		throw new RuntimeException("setVirtualHost(String)");
	}

	@Override
	public void shutdownOutput() {
		throw new RuntimeException("shutdownOutput()");
	}

	@Override
	public void tunnelCreated() throws IllegalStateException, IOException {
		throw new RuntimeException("tunnelCreated()");
	}

	@Override
	public void write(byte[] data, int offset, int length) throws IOException, IllegalStateException {
		throw new RuntimeException("write(byte[], int, int): " + new String(data) + ", " + offset + ", " + length);
	}

	@Override
	public void write(byte[] data) throws IOException, IllegalStateException {
		throw new RuntimeException("write(byte[]): " + new String(data));
	}

	@Override
	public void writeLine() throws IOException, IllegalStateException {
		// Safe call, new line being inserted automatically by the HTTPRequest renderer
	}

	@Override
	public void writeLine(byte[] data) throws IOException, IllegalStateException {
		throw new RuntimeException("writeLine(byte[]): " + new String(data));
	}
}
