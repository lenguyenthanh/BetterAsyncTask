package com.android.tekreds.network.exception;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Occurs when parser error
 * */
public class ParserException extends Exception {

	/**
	 * 
	 */
	public static final long serialVersionUID = -2927868580802388742L;

	public ParserException() {
		super();
	}

	public ParserException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ParserException(String detailMessage) {
		super(detailMessage);
	}

	public ParserException(Throwable throwable) {
		super(throwable);
	}

}
