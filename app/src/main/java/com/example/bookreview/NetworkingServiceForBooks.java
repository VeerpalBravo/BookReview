package com.example.bookreview;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NetworkingServiceForBooks {
    Handler handler = new Handler(Looper.getMainLooper());
    interface NetworkingListener{
        void jsonValuesFetched(String jsonString);
        void gettingImageIsCompleted(Bitmap image);
    }
    NetworkingListener listener;

    String stringValue = " ";
    String url = "https://www.googleapis.com/books/v1/volumes?q=";
    void getAllBooks(String book_name){
        String completeURL=url+book_name;
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL urlObject = new URL(completeURL);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();
                    while((value=inputStream.read())!=-1){
                        stringValue= String.valueOf(buffer.append((char)value));
                    }


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.jsonValuesFetched(stringValue);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    void gettingImage(String imgURL) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL url = new URL(imgURL);
                    //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = url.openStream();//new BufferedInputStream(urlConnection.getInputStream());
                    Bitmap imageData = BitmapFactory.decodeStream(in);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingImageIsCompleted(imageData);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
