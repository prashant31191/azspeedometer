package com.azspeedometer;

import android.app.Application;

import com.roomdb.MyRoomDatabase;

import java.util.Random;

public class App extends Application {

    public static final String TABLE_NAME_NOTE ="tbl_notes";
    public static final String TABLE_NAME_ROUTE ="tbl_route";
    public static final String DB_NAME ="notesdb.db";


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

    public static MyRoomDatabase myRoomDatabase;

    @Override
    public void onCreate() {
        try {
            super.onCreate();

            myRoomDatabase = MyRoomDatabase.getInstance(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
