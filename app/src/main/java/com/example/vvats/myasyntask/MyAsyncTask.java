package com.example.vvats.myasyntask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vvats on 20/03/17.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {

    Context context;
    ProgressDialog progressDialog;

    MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(1000);
                publishProgress(i);
                Log.i("Thread", "Executed " + i);
            }
            return "Successful";
        } catch (Exception e) {
            return "Failure";
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(10);
        progressDialog.setMessage("Please Wait ....");
        progressDialog.setTitle("Downloading");
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        progressDialog.setProgress(value);
    }
}
