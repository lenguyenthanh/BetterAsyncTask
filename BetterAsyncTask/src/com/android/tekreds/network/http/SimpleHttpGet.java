package com.android.tekreds.network.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Simple HttpGet request
 * */

 public class SimpleHttpGet extends AbstractHttpRequest {


	public SimpleHttpGet() {
		super();
	}

	@Override
	protected HttpRequestBase createRequest(String url) {
		request = new HttpGet(url);
		return request;
	}

	@Override
	protected HttpClient createClient() {
		client = new DefaultHttpClient(new NetworkTimeout().getHttpParams());
		return client;
	}
}
