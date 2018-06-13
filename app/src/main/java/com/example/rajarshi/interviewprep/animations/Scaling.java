package com.example.rajarshi.interviewprep.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class Scaling {

    public static void scaleUpDown(View view) {
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "ScaleX", 1.0f, 1.1f, 1.0f);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        scaleX.setStartDelay(0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "ScaleY", 1.0f, 1.1f, 1.0f);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        scaleY.setStartDelay(0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(2000);
        set.setInterpolator(linearInterpolator);
        set.start();
    }

}
