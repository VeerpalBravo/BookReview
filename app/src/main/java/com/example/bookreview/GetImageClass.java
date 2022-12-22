package com.example.bookreview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageClass {
    String url;

    public GetImageClass(String url) {
        this.url = url;
    }

    Handler handler = new Handler(Looper.getMainLooper());
    interface getImageClassListener{
        void imageFetched(String jsonString);
    }
//    GetImageClass.getImageClassListener listener;
//    MyApp.executorService.execute(new Runnable() {
//        @Override
//        public void run() {
//            try {
//
//            }
}
