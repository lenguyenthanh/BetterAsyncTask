package com.android.tekreds.network.http;

import java.io.InputStream;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 * Interface for all http request
 * */
public interface IHttpRequest {
    /**
     * Send request to server
     *
     * @return InputStream
     * @throws java.io.IOException
     * @throws Exception
     * */
    public InputStream requestStream(String url) throws Exception;

    /**
     * Set request header
     * */
    public void setHeader(String key, String value);
}

