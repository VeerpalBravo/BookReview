package com.example.bookreview.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookreview.Book;
import com.example.bookreview.BookInfoRecyclerView;
import com.example.bookreview.BookReviewCommentsActivity;
import com.example.bookreview.Comments;
import com.example.bookreview.DBManager;
import com.example.bookreview.FavBookRecyclerView;
import com.example.bookreview.FavBooks;
import com.example.bookreview.MainActivity;
import com.example.bookreview.MyApp;
import com.example.bookreview.R;

import java.util.ArrayList;
import java.util.Arrays;


public class FavouritesFragment extends Fragment implements  FavBookRecyclerView.ItemClickListener,
        DBManager.DataBaseListener {

    RecyclerView favBookList;
    FavBookRecyclerView adapter;
    ArrayList<FavBooks> favBookArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favourites, container, false);
        favBookList=view.findViewById(R.id.favbook_list);
        ((MyApp)getActivity().getApplication()).db.getAllFavBooks();
        ((MyApp)getActivity().getApplication()).db.listener=this;
        ((MyApp)getActivity().getApplication()).db.getBookDB(getActivity().getApplicationContext());
        adapter = new FavBookRecyclerView(favBookArrayList, getActivity().getApplicationContext());
        adapter.listener=this;
        favBookList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        favBookList.setLayoutManager(linearLayoutManager);
        return view;

    }

    @Override
    public void onItemClick(int pos) {

    }

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
        favBookArrayList = new ArrayList( Arrays.asList(list));
        adapter.favbook_list = favBookArrayList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void gettingFavBooksTitleCompleted(FavBooks[] list) {

    }
}