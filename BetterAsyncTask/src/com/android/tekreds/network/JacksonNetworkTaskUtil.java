package com.android.tekreds.network;

import android.content.Context;
import com.android.tekreds.network.commands.ICommand;
import com.android.tekreds.network.commands.JacksonCommand;
import com.android.tekreds.network.http.IHttpRequest;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Helper class that creates a super fast Jackson command
 * */
public class JacksonNetworkTaskUtil {

	/**
	 * Return network task using Jackson command
	 * 
	 * Require using jackson as a json parser library
	 * */
	public static <T> NetworkTask<Void, Void, T> getNetworkTask(
			Context context, IHttpRequest request, String url, Class<T> clazz) {
		// create command for upload task
		ICommand<T> command = new JacksonCommand<T>(request, clazz);

		return new TemplateTask<T>(context, url, command);
	}
}
