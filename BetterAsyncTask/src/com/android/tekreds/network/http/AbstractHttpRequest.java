package com.android.tekreds.network.http;

import android.util.Log;

import com.android.tekreds.network.exception.ClientProtocolException;
import com.android.tekreds.network.exception.ExceptionUtil;
import com.android.tekreds.network.exception.ServerException;
import com.android.tekreds.network.exception.TimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *
 * Abstract class template for all http request class
 * Handle exception, request header, Network time out.
 * Write some default for faster use like: json header default.
 * Using this class instead of original HttpGet or HttpPost class
 * */


 public abstract class AbstractHttpRequest implements IHttpRequest {
	
	private static final String TAG = AbstractHttpRequest.class.getSimpleName();

    private Map<String, String> mHeaders = new HashMap<String, String>();

    // base interface for http request (HttpGet, HttpPost)
	protected HttpRequestBase request;
	protected HttpClient client;

	// status of current request
	private boolean canceled = false;

    public boolean isCanceled(){
        return canceled;
    }

    public void setCanceled(final boolean aCanceled){
        canceled = aCanceled;
    }

    // Set header for the request
    private void setHeaders() {
        if(!mHeaders.isEmpty()) {
            Set<String> keys = mHeaders.keySet();
            for(String key : keys) {
                request.setHeader(key, mHeaders.get(key));
            }
        }
    }
    @Override
    public void setHeader(final String key, final String value){
        mHeaders.put(key,value);
    }

	/**
	 * Create request from url
	 * */
	protected abstract HttpRequestBase createRequest(String url);

	/**
	 * Create http client to call current request
	 * */
	protected abstract HttpClient createClient();

	/**
	 * Defaul is Json request
	 * */
	public AbstractHttpRequest() {
		super();

	}

	/**
	 * Send request to server
	 * 
	 * @return InputStream
	 * */
	@Override
	public InputStream requestStream(String url) throws Exception{

			Log.i(TAG, "sendMessage: " + url);

		canceled = false;
		// create http request
		HttpRequestBase request = createRequest(url);
        setHeaders();
		// create http client
		HttpClient client = createClient();
		// http respond: handle data from server
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response == null) {
				// cause by network or server

					Log.e(TAG, "Request respond is null: " + url);

				throw new ServerException("Request respond is null.");
			} else if (response.getStatusLine() == null) {
				// rarely happen.

					Log.e(TAG, "Request respond status line is null: " + url);

				throw new ServerException(
						"Request respond status line is null.");
			} else if (HttpUtils.isOkCode(response.getStatusLine().getStatusCode())) {
				// Usually cause by client

					Log.e(TAG, "Request respond code is not OK: " + url + " "
							+ response.getStatusLine().getStatusCode());

				throw new ClientProtocolException(
						"Request respond code is not OK: "
								+ response.getStatusLine().getStatusCode());
			} else {
				// status return OK
				// return content respond: input stream
				return response.getEntity().getContent();
			}
		} catch (IOException exception) {
			// cause by network or server
			if (!canceled) {

					Log.e(TAG, "IOException", exception);

			}
			if (ExceptionUtil.isServerException(exception)) {
				/**
				 * A connection timeout occurs only upon starting the TCP
				 * connection. This usually happens if the remote machine does
				 * not answer. This means that the server has been shut down,
				 * you used the wrong IP/DNS name or the network connection to
				 * the server is down.
				 * */
				throw new ServerException(exception);

			} else if (exception instanceof SocketTimeoutException) {
				/**
				 * A socket timeout is dedicated to monitor the continuous
				 * incoming data flow. If the data flow is interrupted for the
				 * specified timeout the connection is regarded as
				 * stalled/broken. Of course this only works with connections
				 * where data is received all the time.
				 * */
				throw new TimeoutException(exception);
			}
			throw exception;
		}
	}
}
