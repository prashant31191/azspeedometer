package com.azspeedometer;

import android.app.Application;

import java.util.Random;

public class App extends Application {
    static String strArrBnr[] = {
            "ca-app-pub-4346653435295459/1604756797",
            "ca-app-pub-4346653435295459/3464633376",
            "ca-app-pub-4346653435295459/4366242389",

            "ca-app-pub-4346653435295459/1604756797",
            "ca-app-pub-4346653435295459/3464633376",
            "ca-app-pub-4346653435295459/4366242389"

    };

    static String strArrIntAds[] = {
            "ca-app-pub-4346653435295459/7187136409",
            "ca-app-pub-4346653435295459/1740079045",
            "ca-app-pub-4346653435295459/7187136409",
            "ca-app-pub-4346653435295459/1740079045"

    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getBannerId() {
        Random random = new Random();
        int pos = random.nextInt(strArrBnr.length);
        return strArrBnr[pos];
    }

    public static String getInterstitialAdId() {
        Random random = new Random();
        int pos = random.nextInt(strArrIntAds.length);
        return strArrIntAds[pos];
    }


}
