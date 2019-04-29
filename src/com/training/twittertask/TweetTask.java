package com.training.twittertask;

import com.training.alert.PostToTwitterDialog;

import android.os.AsyncTask;
import android.util.Log;

public class TweetTask extends AsyncTask<String, Void, String> {
	String wells=null;
	@Override
	protected String doInBackground(String... str) {
		try {
			wells=Twitter.post(str[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return(wells);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.i("trial", result+ "sdfsdf");
	}
}
