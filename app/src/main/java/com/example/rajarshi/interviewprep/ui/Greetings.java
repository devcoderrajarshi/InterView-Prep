package com.example.rajarshi.interviewprep.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rajarshi.interviewprep.R;
import com.example.rajarshi.interviewprep.animations.Scaling;
import com.example.rajarshi.interviewprep.animations.Translating;
import com.example.rajarshi.interviewprep.utils.ApiUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import su.levenetc.android.badgeview.BadgeView;

public class Greetings extends AppCompatActivity {

    private BadgeView intro;
    private ImageView startLearnCoding;
    private LinearLayout bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
        intro = findViewById(R.id.badgeViewIntro);
        startLearnCoding = findViewById(R.id.startLearnCoding);
        bannerAd = findViewById(R.id.bannerAd);

        startLearnCoding.postDelayed(new Runnable() {
            @Override
            public void run() {
                startLearnCoding.setVisibility(View.VISIBLE);
                startLearnCoding.animate()
                        .alpha(1.0f)
                        .setDuration(1500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                Scaling.scaleUpDown(startLearnCoding);
                            }
                        });
            }
        }, 5000);


        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ApiUtils.bannerAdUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(Intent.createChooser(intent, "Open with a browser"));
            }
        });

        intro.postDelayed(new Runnable() {
            @Override
            public void run() {
                new BadgeView.AnimationSet(intro)
                        .add("Welcome", 600)
                        .addDelay(200)
                        .add("Press Start to Continue", 800)
                        .play();
            }
        }, 2000);


        startLearnCoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Greetings.this, Questions.class));
                finish();
            }
        });

    }
}
