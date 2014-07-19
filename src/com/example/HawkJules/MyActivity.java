package com.example.HawkJules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity implements OnSeekBarChangeListener {

    private SeekBar sbLoveBar;
    private TextView tvScore;
    private ImageView ivHeart;
    private int loveNr;

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

    }

    public void changeLoveClick(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"hakongimse@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
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
}
