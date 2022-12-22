package com.example.bookreview;

import android.app.Application;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {
    public int pos;
    SearchedBooks sb = new SearchedBooks();
    JsonService jsonService = new JsonService();
    public DBManager db = new DBManager();
    static ExecutorService executorService = Executors.newFixedThreadPool(4);
    NetworkingServiceForBooks networkingServiceForBooks = new NetworkingServiceForBooks();
    String query = " ";


}
