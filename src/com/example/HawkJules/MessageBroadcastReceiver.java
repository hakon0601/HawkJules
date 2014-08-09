package com.example.HawkJules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class MessageBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "#MessageBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "received");
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "my action: " + bundle.getString("my_action"));
        //hei julie jeg elsker deg skikkelig mye og tenker på deg hele tiden mens jeg sitter her oppe uten deg.
        //Jeg vil at du skal vite hvor mye du betyr for meg og at bare med din tilstedeværelse løfter livet
        //mitt. Du er en nusselig liten kosepus! gleder meg til å ha deg her igjen! Forresten, det er denne klassen
        //som mottar det vi sender med knappen vår. Good luck.
        //Jeg elsker deg så utrolig mye. Tenker på deg hvert minutt av hver time hver dag. Det er vondt å være uten deg,
        //og jeg gleder så mye til å hoppe i armene dine igjen. Du er den beste kjæresten! Elsker deg!
        //Julie elsker Håkon
    }
}
