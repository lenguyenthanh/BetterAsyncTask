package com.android.tekreds.network.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Simple httpPost request
 * */

 public class SimpleHttpPost extends AbstractHttpRequest {

	private AbstractHttpEntity entity;

	public SimpleHttpPost() {
		super();
	}

	public SimpleHttpPost(AbstractHttpEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected HttpRequestBase createRequest(String url) {

		HttpPost post = new HttpPost(url);
		if (entity != null) {
			post.setEntity(entity);
		}
		request = post;
		return request;
	}

	@Override
	protected HttpClient createClient() {
		client = new DefaultHttpClient(new NetworkTimeout().getHttpParams());
		return client;
	}

	public void setEntity(AbstractHttpEntity entity) {
		this.entity = entity;
	}

}
