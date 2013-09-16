package com.android.tekreds.network.commands;

import com.android.tekreds.network.exception.ParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 * Interface for all command execute http request
 * */

 public interface ICommand<T> {
	/**
	 * parser input stream to object
	 * 
	 * @return T
	 * @throws ParserException
	 * @throws java.io.IOException
	 * */
	public T streamToObject(InputStream in) throws IOException, ParserException;

	/**
	 * all process to fetch object from server
	 *
	 * @param url
	 *            an absolute URL
	 * @return T an object
	 * @throws java.io.IOException
	 * @throws ParserException
	 * @throws Exception 
	 * */
	public T execute(String url) throws IOException, ParserException, Exception;

}
