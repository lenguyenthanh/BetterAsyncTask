package com.android.tekreds.network;

import android.content.Context;
import android.os.AsyncTask;
import com.android.tekreds.network.exception.NetworkException;
import com.android.tekreds.network.exception.ParserException;

import java.io.IOException;

/**
 *  @author Thanh Le
 *  @twitter: @lenguyenthanh
 *  Alternative for Asynctask
 * */

 abstract public class NetworkTask<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result>{

	public static interface OnCompleteListener<Result> {
		public void onComplete(Result result);
	}

	public static interface OnParserExceptionListener {
		public void onParserException(ParserException parserException);
	}

	public static interface OnIOExceptionListener {
		public void onIOException(IOException exception);
	}

	public static interface OnExceptionListener {
		public void onException(Exception exception);
	}

	public static interface OnNetworkUnavailableListener {
		public void onNetworkException(NetworkException exception);
	}

	private Exception exception;
	private IOException ioException;
	private ParserException parserException;

	private boolean isComplete = false;

	public boolean isComplete() {
		return isComplete;
	}

	private boolean isAborted = false;

	public boolean isAborted() {
		return isAborted;
	}

	private OnCompleteListener<Result> completeListener;

	public void setOnCompleteListener(
			OnCompleteListener<Result> completeListener) {
		this.completeListener = completeListener;
	}

	private OnExceptionListener exceptionListener;

	public void setOnExceptionListener(OnExceptionListener l) {
		this.exceptionListener = l;
	}

	private OnExceptionListener genericExceptionListener;

	/**
	 * This listener gets called if any error happens. It's a convenience method
	 * to catch all the errors in 1 shot.
	 * 
	 * @param l
	 */
	public void setOnGenericExceptionListener(OnExceptionListener l) {
		this.genericExceptionListener = l;
	}

	private OnIOExceptionListener ioExceptionListener;

	public void setOnIOExceptionListener(OnIOExceptionListener l) {
		this.ioExceptionListener = l;
	}

	private OnParserExceptionListener parserExceptionListener;

	public void setOnParserExceptionListener(OnParserExceptionListener l) {
		this.parserExceptionListener = l;
	}

	private OnNetworkUnavailableListener networkUnavailableListener;

	public void setOnNetworkUnavailableListener(
			OnNetworkUnavailableListener networkUnavailableListener) {
		this.networkUnavailableListener = networkUnavailableListener;
	}

	/**
	 * Context for check network available
	 * */
	private Context context;

	public NetworkTask(Context context) {
		super();
		this.context = context;
	}

	/**
	 * A convenience method used to hide the poor API of the internal execute
	 * method that can't be overridden.
	 */
	@SuppressWarnings("unchecked")
	public void execute() {
		execute(null, null);
	}

	/**
	 * Silly AsynTask has the cancel marked as final. Use abort instead;
	 */
	public void abort() {
		isAborted = true;
		cancel(true);
	}

	/**
	 * This is where we make the network call. We're not passing object here, so
	 * this method must get the params it needs from the class properties. Since
	 * this is thread be sure to make as volatile if needed.
	 * 
	 * @return
	 * @throws ParserException
	 * @throws Exception
	 */
	abstract protected Result loadDataFromNetwork() throws IOException,
			ParserException, Exception;

	/**
	 * This method runs on the UI Thread. Use this hook for what happens when
	 * the loadDataFromNetwork method returns successfully.
	 * 
	 * @param result
	 *            The result from loadDataFromNetwork
	 */
	protected void onPostSuccess(Result result) {
	}

	protected void onPostFault(Exception e) {
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		isComplete = false;
		isAborted = false;
		boolean hasNetworkConnection = NetworkUtil.hasInternetAccess(context);
		if (!hasNetworkConnection) {
			if (networkUnavailableListener != null) {
				networkUnavailableListener
						.onNetworkException(new NetworkException(
								"Internet connection unavailable"));
			}
			abort();
		}
	}

	/**
	 * Mostly likely you should not override this. It's not marked as final, but
	 * treat it like that.
	 */
	@Override
	protected Result doInBackground(Params... params) {
		if (isCancelled()) {
			return null;
		}
		try {
			return loadDataFromNetwork();
		} catch (ParserException e) {
			parserException = e;
			return null;
		} catch (IOException e) {
			ioException = e;
			return null;
		} catch (Exception e) {
			exception = e;
			return null;
		}
	}

	/**
	 * Out logic to figure what kind of result we got.
	 */
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		isComplete = true;
		if (isCancelled() || isAborted()) {
			return;
		}

		if (parserException != null) {
			onPostFault(parserException);
			if (parserExceptionListener != null)
				parserExceptionListener.onParserException(parserException);
			if (genericExceptionListener != null)
				genericExceptionListener.onException(parserException);
		} else if (ioException != null) {
			onPostFault(ioException);
			if (ioExceptionListener != null)
				ioExceptionListener.onIOException(ioException);
			if (genericExceptionListener != null)
				genericExceptionListener.onException(ioException);
		} else if (exception != null) {
			onPostFault(exception);
			if (exceptionListener != null)
				exceptionListener.onException(exception);
			if (genericExceptionListener != null)
				genericExceptionListener.onException(exception);
		}

		// SUCCESS!
		else {
			onPostSuccess(result);
			if (completeListener != null) {
				completeListener.onComplete(result);
			}
		}
	}
}