package com.android.tekreds.network.exception;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 * */

 public class ExceptionUtil {
	public static boolean isServerException(Exception exception) {
		if (exception instanceof ConnectTimeoutException
				|| exception instanceof HttpHostConnectException) {
			return true;
		} else {
			return false;
		}
	}
	
}
