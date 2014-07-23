package com.example.HawkJules;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class RegisterTask extends AsyncTask<Void, Void, Void> {

    public static final String PROPERTY_REGID = "regid";


    private RegisterListener listener;
    private GoogleCloudMessaging gcm;
    private final String TAG = "#RegisterTask";
    private String regid;
    private Context context;

    public RegisterTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (!hasRegId()) {
            regid = getRegid();
            saveRegIdToSharedPreferences();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onRegisterComplete(regid);
    }

    private boolean hasRegId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.examples.HawkJules", context.MODE_PRIVATE);
        regid = sharedPreferences.getString(PROPERTY_REGID, null);
        if (regid != null)
            return true;
        return false;
    }

    private String getRegid() {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        try {
            String newRegid = gcm.register(context.getResources().getString(R.string.project_number));
            return newRegid;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveRegIdToSharedPreferences() {
        if (regid != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.examples.HawkJules", context.MODE_PRIVATE);
            sharedPreferences.edit().putString(PROPERTY_REGID, regid);
        }
    }

    public void setListener(RegisterListener listener) {
        this.listener = listener;
    }
}
