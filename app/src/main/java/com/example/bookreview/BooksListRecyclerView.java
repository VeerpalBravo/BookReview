package com.example.bookreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class BooksListRecyclerView extends AppCompatActivity implements NetworkingServiceForBooks.NetworkingListener,
        BookInfoRecyclerView.ItemClickListener{

    RecyclerView bookList;
    String query;
    BookInfoRecyclerView adapter;
    int toggleBtnValue;
    ArrayList<FavBooks> bookIDArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list_recycler_view);
        ((MyApp)getApplication()).networkingServiceForBooks.listener=this;
        setTitle("Book List");
        ((MyApp)getApplication()).sb.getBookList().clear();
        ((MyApp)getApplication()).sb.getFullDescBookList().clear();
        Intent intent = getIntent();
        query = intent.getStringExtra("query");

   }
   protected void setAdapterFunc(String newQuery){

       ((MyApp)getApplication()).networkingServiceForBooks.getAllBooks(newQuery);
       bookList = findViewById(R.id.book_list);
       adapter = new BookInfoRecyclerView(((MyApp)getApplication()).sb
               .getBookList(), this);
       adapter.listener=this;
       bookList.setAdapter(adapter);
       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
       bookList.setLayoutManager(linearLayoutManager);
   }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapterFunc(((MyApp)getApplication()).query);
        Log.d("queryValueResume",((MyApp)getApplication()).query);
    }


    @Override
    public void onItemClick(int pos) {
        Intent intent=new Intent(BooksListRecyclerView.this,PerformFunctionOnSelectedBookActivity.class);
        ((MyApp)getApplication()).pos=pos;
        startActivity(intent);
    }

    @Override
    public void jsonValuesFetched(String jsonString) {
        ((MyApp)getApplication()).jsonService.getBookTitles(jsonString, this);
        adapter.book_list=((MyApp)getApplication()).sb.getBookList();
        int size=adapter.book_list.size();
        Log.d("NewValues:", String.valueOf(size));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void gettingImageIsCompleted(Bitmap image) {

    }

}