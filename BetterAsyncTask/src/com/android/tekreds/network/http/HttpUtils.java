package com.android.tekreds.network.http;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 * */
public class HttpUtils {
	public static boolean isOkStatus(int statusCode) {
		return (statusCode < 300 && statusCode >= 200);
	}
}
