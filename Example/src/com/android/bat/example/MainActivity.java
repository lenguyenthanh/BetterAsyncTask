package com.android.bat.example;

import java.io.IOException;

import com.android.bat.example.pojos.Time;
import com.android.tekreds.network.JacksonNetworkTaskUtil;
import com.android.tekreds.network.NetworkTask;
import com.android.tekreds.network.NetworkTask.OnCompleteListener;
import com.android.tekreds.network.NetworkTask.OnExceptionListener;
import com.android.tekreds.network.NetworkTask.OnNetworkUnavailableListener;
import com.android.tekreds.network.exception.ClientProtocolException;
import com.android.tekreds.network.exception.NetworkException;
import com.android.tekreds.network.exception.ParserException;
import com.android.tekreds.network.exception.ServerException;
import com.android.tekreds.network.exception.TimeoutException;
import com.android.tekreds.network.http.IHttpRequest;
import com.android.tekreds.network.http.SimpleHttpGet;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String URL = "http://date.jsontest.com";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void loadData() {
		final ProgressDialog dialog = ProgressDialog.show(this, "",
				"Loading...", true);
		dialog.show();
		
		// create a Http request
		IHttpRequest request = new SimpleHttpGet();
		// set request for Json
		request.setHeader("Accept", "application/json");
		
		// get network task by JacksonNetworkTaskUtil
		NetworkTask<Void, Void, Time> task = JacksonNetworkTaskUtil.getNetworkTask(this, request, URL, Time.class);
		
		task.setOnCompleteListener(new OnCompleteListener<Time>() {

			@Override
			public void onComplete(Time result) {				
				// load success
				((TextView)findViewById(R.id.tvWeather)).setText("Server Time: " + result.getTime() + " " + result.getDate());
				dialog.cancel();
			}
		});
		
		task.setOnExceptionListener(new OnExceptionListener() {

			@Override
			public void onException(Exception exception) {
				// There are 5 kinds of exception 
				dialog.cancel();
				if (exception instanceof ServerException) {
					((TextView)findViewById(R.id.tvWeather)).setText("There are some errors in our server. Please try again.");
		            
		        } else if (exception instanceof ParserException) {
		        	((TextView)findViewById(R.id.tvWeather)).setText("There are some errors when exchange data with server. Please try again.");
		            
		        } else if (exception instanceof TimeoutException) {
		        	((TextView)findViewById(R.id.tvWeather)).setText("Connection timeout. Please try again.");
		            
		        } else if (exception instanceof ClientProtocolException) {
		        	((TextView)findViewById(R.id.tvWeather)).setText( "There are some errors when exchange data with server. Please try again.");
		            
		        } else if (exception instanceof IOException) {
		        	((TextView)findViewById(R.id.tvWeather)).setText("There are some errors. Please try again.");
		            
		        } else {
		            // Exception
		        	((TextView)findViewById(R.id.tvWeather)).setText("There are some errors. Please try again.");
		            
		        }
			}
		});
		
		task.setOnNetworkUnavailableListener(new OnNetworkUnavailableListener() {

			@Override
			public void onNetworkException(NetworkException exception) {	
				// There is no network connection
				((TextView)findViewById(R.id.tvWeather)).setText("There is no internet connection.");
			}
		});
	}
	
	
}
