package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  No Network connection
 * */

 public class NetworkException extends Exception{
    /**
	 * 
	 */
	public static final long serialVersionUID = 4976930440939414626L;

	public NetworkException() {
    }

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }

    public NetworkException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetworkException(Throwable throwable) {
        super(throwable);
    }
}
