package com.example.rajarshi.interviewprep.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rajarshi.interviewprep.R;
import com.example.rajarshi.interviewprep.recyclerViewAdapter.InterviewAdapter;
import com.example.rajarshi.interviewprep.recyclerViewAdapter.QuestionAnswerModel;
import com.example.rajarshi.interviewprep.services.APIResult;
import com.example.rajarshi.interviewprep.services.APIService_Volley_JSON;
import com.example.rajarshi.interviewprep.utils.ApiUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Questions extends AppCompatActivity implements APIResult, SwipeRefreshLayout.OnRefreshListener {


    private boolean flag = true;
    private List<QuestionAnswerModel> questionAnswersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InterviewAdapter mAdapter;
    private boolean doubleBackToExitPressedOnce = false;
    private SwipeRefreshLayout refreshLayout;
    private ShimmerFrameLayout titleShimmer;
    private LinearLayout bannerAd;
    public Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        ctx =this;

        titleShimmer = findViewById(R.id.shimmerTitleQuestions);
        bannerAd = findViewById(R.id.bannerAdQuestions);

        //Shimmer Effect
        titleShimmer.setBaseAlpha(0.5f);
        titleShimmer.setDuration(2500);
        titleShimmer.startShimmerAnimation();


        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ApiUtils.bannerAdUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(Intent.createChooser(intent, "Open with a browser"));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.questionsListRecycler);
        refreshLayout = findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(this);
        mAdapter = new InterviewAdapter(questionAnswersList,ctx);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && flag) {
                    Toasty.success(Questions.this, "Great ! You have finished all questions", Toast.LENGTH_SHORT).show();
                    flag = false;

                }
            }
        });
        recyclerView.setAdapter(mAdapter);
        fetchData();


    }


    @Override
    public void getResult(boolean isSuccess, final String result) {
        try {
            if (isSuccess) {
                questionAnswersList.clear();
                final JSONObject json = new JSONObject(result);
                Log.d("TAG", "getResult: " + json);

                JSONArray questionsArray = json.getJSONArray("questions");
                for (int i = 0; i < questionsArray.length(); i++) {
                    Log.d("Array", "value: " + questionsArray.getJSONObject(i).getString("question"));
                    Log.d("Array", "value: " + questionsArray.getJSONObject(i).getString("Answer"));
                    String q = questionsArray.getJSONObject(i).getString("question").trim();
                    String a = questionsArray.getJSONObject(i).getString("Answer").trim();
                    QuestionAnswerModel qa = new QuestionAnswerModel(q, a);
                    questionAnswersList.add(qa);
                }
                mAdapter.notifyDataSetChanged();


            } else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toasty.error(Questions.this, " " + result, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (final JSONException e) {
            e.printStackTrace();
        }
    }


    public void fetchData() {
        new APIService_Volley_JSON(Questions.this, this).execute(ApiUtils.APIPath);
    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

        } else {
            this.doubleBackToExitPressedOnce = true;
            Toasty.warning(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }

        }, 1000);
        fetchData();
        mAdapter.notifyDataSetChanged();
    }
}
