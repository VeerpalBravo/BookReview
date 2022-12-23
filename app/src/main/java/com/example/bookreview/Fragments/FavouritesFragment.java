package com.example.bookreview.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;


public class FavouritesFragment extends Fragment implements  FavBookRecyclerView.ItemClickListener,
        DBManager.DataBaseListener {

    RecyclerView favBookList;
    FavBookRecyclerView adapter;
    ArrayList<FavBooks> favBookArrayList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    FrameLayout flfav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favourites, container, false);
        favBookList=view.findViewById(R.id.favbook_list);
        flfav=view.findViewById(R.id.flFav);
        ((MyApp)getActivity().getApplication()).db.listener=this;
        ((MyApp)getActivity().getApplication()).db.getBookDB(getActivity().getApplicationContext());
        ((MyApp)getActivity().getApplication()).db.getAllFavBooks();
        adapter = new FavBookRecyclerView(favBookArrayList, getActivity().getApplicationContext());
        adapter.listener=this;
        favBookList.setAdapter(adapter);
        linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        favBookList.setLayoutManager(linearLayoutManager);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(favBookList);
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

    @Override
    public void deleteFavBookCompleted() {
        ((MyApp)getActivity().getApplication()).db.getAllFavBooks();
    }

    ItemTouchHelper.SimpleCallback callback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar.make(flfav,
                    "Item deleted",Snackbar.LENGTH_SHORT).show();
            ((MyApp)getActivity().getApplication()).toggleBtn=0;
           // favBookArrayList.remove(viewHolder.getAbsoluteAdapterPosition());
           // adapter.notifyDataSetChanged();
            ((MyApp)getActivity().getApplication()).db.deleteFavBook(adapter.getFavbooksPosition(
                    viewHolder.getAbsoluteAdapterPosition()));
        }
    };
}