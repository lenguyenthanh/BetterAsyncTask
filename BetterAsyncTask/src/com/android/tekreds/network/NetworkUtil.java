package com.android.tekreds.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *
 * */
public class NetworkUtil{
	/**
	 * Check if network available
	 * 
	 * @return true if available else return false
	 * 
	 * */

	public static boolean hasInternetAccess(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			// getSystemService return null
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
