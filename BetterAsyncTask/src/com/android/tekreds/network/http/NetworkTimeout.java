package com.android.tekreds.network.http;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Set timeout for HttpRequest
 * */

 public class NetworkTimeout {
    // default time
	private static int TIME0UT_CONNECTION = 6000;
	private static int TIME0UT_SOCKET = 15000;

	private int connectionTimeout;
	private int socketTimeout;

	public NetworkTimeout(int connectionTimeout, int socketTimeout) {
		super();
		this.connectionTimeout = connectionTimeout;
		this.socketTimeout = socketTimeout;
	}

	public NetworkTimeout() {
		// use default connection time out
		this(TIME0UT_CONNECTION, TIME0UT_SOCKET);
	}

	/**
	 * set time out for a connection
	 * */
	public HttpParams getHttpParams() {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				connectionTimeout);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, socketTimeout);
		return httpParameters;
	}
}
