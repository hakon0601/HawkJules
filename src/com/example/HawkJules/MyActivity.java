package com.example.HawkJules;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyActivity extends Activity implements OnSeekBarChangeListener, RegisterListener, SendLoveListener {

    private final String TAG = "#MyActivity";
    private SeekBar sbLoveBar;
    private TextView tvScore;
    private ImageView ivHeart;
    private int loveNr;
    private String regid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sbLoveBar = (SeekBar) findViewById(R.id.sbLoveBar);
        tvScore = (TextView) findViewById(R.id.tvScore);
        ivHeart = (ImageView) findViewById(R.id.ivHeart);
        ivHeart.setImageResource(R.drawable.heart);
        ivHeart.setScaleX(0);
        sbLoveBar.setOnSeekBarChangeListener(this);

        RegisterTask registerTask = new RegisterTask(this);
        registerTask.setListener(this);
        registerTask.execute();
    }

    public void changeLoveClick(View view) {
        SendLoveTask sendLoveTask = new SendLoveTask(this, regid);
        sendLoveTask.setListener(this);
        sendLoveTask.execute();

        /*Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"hakongimse@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int pos, boolean b) {
        loveNr = pos;
        tvScore.setText(loveNr + " %");
        ivHeart.setScaleX((float) loveNr/100);
        ivHeart.setScaleY((float) loveNr/100);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void onRegisterComplete(String regid) {
        Log.i(TAG, "regid: " + regid);
        this.regid = regid;
    }

    @Override
    public void onSendLoveComplete() {
        Log.d(TAG, "sending complete");
    }
}
