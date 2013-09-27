package com.android.tekreds.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.tekreds.network.commands.ICommand;

/**
 * Template for all network task
 * Using command interface to parser
 *
 * */

/**
 * @author Thanh Le
 * @twitter: @lenguyenthanh
 * Template for all network task
 * Using command interface to parser
 */

public class TemplateTask<T> extends NetworkTask<Void, Void, T>{

    private static final String TAG = TemplateTask.class.getSimpleName();

    private String url;
    private ICommand<T> command;

    public TemplateTask(Context context, String url, ICommand<T> command){
        super(context);
        this.url = Uri.parse(url).buildUpon().toString();
        this.command = command;
    }

    @Override
    protected T loadDataFromNetwork() throws Exception{
        if(command == null){
            Log.e(TAG, "Command null");
            throw new Exception("Command null exception.");
        }
        return command.execute(url);
    }

    @Override
    public void abort(){
        super.abort();
        if(command != null){
            command = null;
        }
    }
}
