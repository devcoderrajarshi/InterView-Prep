package com.example.rajarshi.interviewprep.services;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajarshi.interviewprep.R;

import org.json.JSONObject;


public class APIService_Volley_JSON extends AsyncTask<String, String, String> {

    public Context mContext;
    String result = "";
    JsonObjectRequest JOR;
    RequestQueue requestQueue;
    private boolean isSuccess = true;
    private Dialog mDialog;
    private APIResult response;


    public APIService_Volley_JSON(Context ctx, APIResult res) {
        mContext = ctx;
        response = res;
        requestQueue = Volley.newRequestQueue(ctx);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (NetworkStatus.isOnline(mContext)) {
            View view = View.inflate(mContext, R.layout.progress_bar, null);
            mDialog = new Dialog(mContext, R.style.dialogwinddow);
            mDialog.setContentView(view);
            mDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (!NetworkStatus.isOnline(mContext)) {
            isSuccess = false;
            response.getResult(false, mContext.getResources().getString(R.string.check_internet_connection));
            result = mContext.getResources().getString(R.string.check_internet_connection);
            return result;
        } else {
            String url = params[0];
            JOR = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject jResponse) {
                    if (mDialog != null) {
                        if (mDialog.isShowing())
                            try {
                                mDialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        mDialog = null;
                    }
                    result = jResponse.toString();
                    response.getResult(isSuccess, jResponse.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mDialog != null || mDialog.isShowing()) {
                        mDialog.dismiss();
                        mDialog = null;
                    }
                    String errorMessage = "Please Try Again, ";
                    if (error instanceof NetworkError) {
                        errorMessage += "Network Connection Error ";
                    } else if (error instanceof ClientError) {
                        errorMessage += "Client Error ";
                    } else if (error instanceof ServerError) {
                        errorMessage += "Server connection Error ";
                    } else if (error instanceof AuthFailureError) {
                        errorMessage += "AuthFailureError ";
                    } else if (error instanceof ParseError) {
                        errorMessage += "ParseError ";
                    } else if (error instanceof NoConnectionError) {
                        errorMessage += "Network Connection Error ";
                    } else if (error instanceof TimeoutError) {
                        errorMessage += "Request Timeout";
                    }
                    response.getResult(false, errorMessage);
                }
            });
            requestQueue.add(JOR);
            JOR.setRetryPolicy(new DefaultRetryPolicy(34000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            return result;
        }

    }

}
