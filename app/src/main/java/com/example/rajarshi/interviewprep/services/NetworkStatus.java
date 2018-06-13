package com.example.rajarshi.interviewprep.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus extends BroadcastReceiver {
    public Context mContext;

    public static boolean isOnline(Context mContext2) {
        if (mContext2 != null) {
            ConnectivityManager connectivity = (ConnectivityManager) mContext2.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }
        }
        return false;
    }

    public static boolean getConnectivityStatus(Context context) {
        boolean conn = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (activeNetwork.isConnected()) {
                    conn = true;
                } else {
                    conn = false;
                }
            }
        } else {
            conn = false;
        }
        return conn;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        try {
            if (!getConnectivityStatus(mContext)) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

