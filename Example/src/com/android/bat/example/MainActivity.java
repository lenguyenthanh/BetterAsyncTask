package com.android.bat.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.bat.example.pojos.Country;
import com.android.bat.example.pojos.World;
import com.android.tekreds.network.JacksonNetworkTaskUtil;
import com.android.tekreds.network.NetworkTask;
import com.android.tekreds.network.NetworkTask.OnCompleteListener;
import com.android.tekreds.network.NetworkTask.OnExceptionListener;
import com.android.tekreds.network.NetworkTask.OnNetworkUnavailableListener;
import com.android.tekreds.network.exception.*;
import com.android.tekreds.network.http.IHttpRequest;
import com.android.tekreds.network.http.SimpleHttpGet;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

	private static final String URL = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

    private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		                    mListView = (ListView) findViewById(R.id.listView);
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
		NetworkTask<Void, Void, World> task = JacksonNetworkTaskUtil.getNetworkTask(this, request, URL, World.class);
		
		task.setOnCompleteListener(new OnCompleteListener<World>() {

			@Override
			public void onComplete(World result) {
				// load success
                ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(MainActivity.this, android.R.layout.simple_list_item_1, result.getWorldpopulation());
                mListView.setAdapter(adapter);
				dialog.cancel();
			}
		});
		
		task.setOnExceptionListener(new OnExceptionListener(){

            @Override
            public void onException(Exception exception){
                // There are 5 kinds of exception
                dialog.cancel();
                if(exception instanceof ServerException){
                    Toast.makeText(MainActivity.this, "There are some errors in our server. Please try again.",
                                   Toast.LENGTH_LONG).show();

                } else if(exception instanceof ParserException){
                    Toast.makeText(MainActivity.this,
                                   "There are some errors when exchange data with server. Please try again.",
                                   Toast.LENGTH_LONG).show();

                } else if(exception instanceof TimeoutException){
                    Toast.makeText(MainActivity.this, "Connection timeout. Please try again.",
                                   Toast.LENGTH_LONG).show();

                } else if(exception instanceof ClientProtocolException){
                    Toast.makeText(MainActivity.this,
                                   "There are some errors when exchange data with server. Please try again.",
                                   Toast.LENGTH_LONG).show();

                } else if(exception instanceof IOException){
                    Toast.makeText(MainActivity.this, "There are some errors. Please try again.",
                                   Toast.LENGTH_LONG).show();

                } else{
                    // Exception
                    Toast.makeText(MainActivity.this, "There are some errors. Please try again.",
                                   Toast.LENGTH_LONG).show();

                }
            }
        });
		
		task.setOnNetworkUnavailableListener(new OnNetworkUnavailableListener() {

			@Override
			public void onNetworkException(NetworkException exception) {	
				// There is no network connection
				Toast.makeText(MainActivity.this, "There is no internet connection.", Toast.LENGTH_LONG).show();
				dialog.cancel();
			}
		});

        // execute task
        task.execute();
	}
	
}
