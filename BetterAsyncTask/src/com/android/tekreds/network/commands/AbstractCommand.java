package com.android.tekreds.network.commands;

import android.util.Log;
import com.android.tekreds.network.http.IHttpRequest;

import java.io.IOException;
import java.io.InputStream;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Template for all Command
 * */

 public abstract class AbstractCommand<T> implements ICommand<T> {

	private static final String TAG = AbstractCommand.class.getSimpleName();

	protected IHttpRequest request;

	public AbstractCommand(IHttpRequest request) {
		super();
		this.request = request;
	}



	/**
	 * @throws Exception
	 * @throws java.io.IOException
	 * 
	 * */
	@Override
	public T execute(String url) throws IOException, Exception {
			Log.i(TAG, "execute " + url);

		InputStream in = request.requestStream(url);
		return streamToObject(in);
	}
}
