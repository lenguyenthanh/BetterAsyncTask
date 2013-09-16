package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  When client using wrong Http Method
 * */

 public class ClientProtocolException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030593935402252775L;

	public ClientProtocolException() {
		super();
	}

	public ClientProtocolException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ClientProtocolException(String detailMessage) {
		super(detailMessage);
	}

	public ClientProtocolException(Throwable throwable) {
		super(throwable);
	}
}
