package com.sequoiahack.storylead.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sequoiahack.storylead.R;

/**
 * This fragment is loaded when user clicks on Record button on BottomBar
 * Created by zac on 10/09/16.
 */
public class RecordFragment extends BaseFragment {
    private ImageView recordStopImageView;
    private TextView recordStatusTextView;
    private boolean recording = false;
    private Chronometer chronometer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.record_fragment, container, false);
        recordStopImageView = (ImageView) layout.findViewById(R.id.recordstopimageview);
        chronometer = (Chronometer) layout.findViewById(R.id.chronometer);


        recordStopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording) {
                    recording = false;
                    recordStopImageView.setImageResource(R.drawable.record);
                    recordStatusTextView.setText("RECORD");
                    stopAnimation();
                    chronometer.stop();
                } else {
                    recording = true;
                    recordStopImageView.setImageResource(R.drawable.stopbutton);
                    recordStatusTextView.setText("RECORDING");
                    startAnimation();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }

            }
        });
        recordStatusTextView = (TextView) layout.findViewById(R.id.recordstatustextview);
        //timerTextView = (TextView) layout.findViewById(R.id.timerTextView);
        if (!recording) {
            recordStopImageView.setImageResource(R.drawable.record);
            recordStatusTextView.setText("RECORD");
        } else {
            recordStopImageView.setImageResource(R.drawable.stopbutton);
            recordStatusTextView.setText("RECORDING");
        }


        return layout;
    }

    public void startAnimation() {
        final Animation animation = new AlphaAnimation((float) 1f, 0.0f); // Change alpha from fully visible to invisible
        animation.setDuration(700); // duration - half a second
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        recordStatusTextView.startAnimation(animation);

    }

    public void stopAnimation() {
        recordStatusTextView.getAnimation().cancel();
        recordStatusTextView.clearAnimation();
        // recordStatusTextView.setAnimationListener(null);
        recordStatusTextView.setAnimation(null);
    }
}
