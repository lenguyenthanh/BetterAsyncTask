package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Time out
 * */

 public class TimeoutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3399769442662307887L;

	public TimeoutException() {
		super();
	}

	public TimeoutException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public TimeoutException(String detailMessage) {
		super(detailMessage);
	}

	public TimeoutException(Throwable throwable) {
		super(throwable);
	}
}
