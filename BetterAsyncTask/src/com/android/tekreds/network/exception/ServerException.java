package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Occurs when server cannot respond or internal server error
 * */


public class ServerException extends Exception {

	/**
	 * 
	 */
	public static final long serialVersionUID = -5383493215450085894L;

	public ServerException() {
		super();
	}

	public ServerException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ServerException(String detailMessage) {
		super(detailMessage);
	}

	public ServerException(Throwable throwable) {
		super(throwable);
	}
	
}
