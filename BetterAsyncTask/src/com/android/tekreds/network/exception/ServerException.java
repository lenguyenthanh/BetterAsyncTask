package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Occurs when server cannot respond or internal server error
 * */


public class ServerException extends Exception {

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
