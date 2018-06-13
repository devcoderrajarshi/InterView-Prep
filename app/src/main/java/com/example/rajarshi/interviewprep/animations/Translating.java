package com.example.rajarshi.interviewprep.animations;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

public class Translating {

    public static void translateY(View view, int from, int to) {
        DecelerateInterpolator interpolator = new DecelerateInterpolator();
        TranslateAnimation animate = new TranslateAnimation(0, 0, from, to);
        animate.setDuration(1000);
        animate.setFillAfter(true);
        animate.setInterpolator(interpolator);
        view.startAnimation(animate);

    }
}
