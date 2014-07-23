package com.example.HawkJules;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class SendLoveTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String regid;
    private SendLoveListener listener;
    private final String TAG = "#SendLoveTask";

    public SendLoveTask(Context context, String regid) {
        this.context = context;
        this.regid = regid;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        Bundle bundle = new Bundle();
        bundle.putString("my_action", "com.google.android.gcm.demo.app.ECHO_NOW");
        try {
            gcm.send(context.getResources().getString(R.string.project_number) + "@gcm.googleapis.com", regid, bundle);
        } catch (IOException e) {
            Log.d(TAG, "exeption on send");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSendLoveComplete();
    }

    public void setListener(SendLoveListener listener) {
        this.listener = listener;
    }
}
