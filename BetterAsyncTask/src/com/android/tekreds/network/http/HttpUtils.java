package com.android.tekreds.network.http;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 * */
class HttpUtils {
	public static boolean isOkCode(int statusCode) {
		return statusCode >= 300 || statusCode < 200;
	}
}
