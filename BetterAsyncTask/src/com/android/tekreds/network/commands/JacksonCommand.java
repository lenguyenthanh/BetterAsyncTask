package com.android.tekreds.network.commands;

import android.util.Log;
import com.android.tekreds.network.exception.ParserException;
import com.android.tekreds.network.http.IHttpRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *
 * A command using jackson parser library
 * Require using jackson as a json parser library
 * */

 public class JacksonCommand<T> extends AbstractCommand<T> {
	private static final String TAG = JacksonCommand.class.getSimpleName();
	private Class<T> clazz;

	public JacksonCommand(IHttpRequest request, Class<T> clazz) {
		super(request);
		this.clazz = clazz;
	}

	@Override
	public T streamToObject(InputStream in) throws IOException, ParserException{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(in, clazz);
		} catch (JsonParseException e) {
			Log.e(TAG, e.toString());
			throw new ParserException(e.toString());
		} catch (JsonMappingException e) {
            Log.e(TAG, e.toString());
			throw new ParserException(e.toString());
		}
	}
}
