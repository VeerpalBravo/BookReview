package com.example.bookreview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.room.Dao;

import java.util.ArrayList;
import java.util.Arrays;

public class BookExixtsInFavoriteList implements DBManager.DataBaseListener {
    public BookExixtsInFavoriteList(Context context) {
        this.context = context;
    }
    public void favToggleBtn() {
        ((MyApp)context.getApplicationContext()).db.listener=this;
        ((MyApp)context.getApplicationContext()).db.getBookDB(context);
        ((MyApp) context.getApplicationContext()).db.getTitleFavBooks();
    }

    ArrayList<FavBooks> bookIDArrayList = new ArrayList<>();
    Context context;

    @Override
    public void insertingCommentsCompleted() {

    }

    @Override
    public void gettingCommentsCompleted(Comments[] list) {

    }

    @Override
    public void insertingBooksCompleted() {

    }

    @Override
    public void gettingFavBooksCompleted(FavBooks[] list) {

    }

    @Override
    public void gettingFavBooksTitleCompleted(FavBooks[] list) {
        bookIDArrayList=new ArrayList( Arrays.asList(list));
        for(int i=0;i<bookIDArrayList.size();i++) {
            ((MyApp) context.getApplicationContext()).
                    sb.bookIDList.add(bookIDArrayList.get(i).getBookID());
        }
    }

    @Override
    public void deleteFavBookCompleted() {

    }
}
