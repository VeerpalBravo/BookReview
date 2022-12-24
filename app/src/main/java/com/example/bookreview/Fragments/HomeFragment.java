package com.example.bookreview.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.bookreview.BooksListRecyclerView;
import com.example.bookreview.Comments;
import com.example.bookreview.DBManager;
import com.example.bookreview.FavBooks;
import com.example.bookreview.MainActivity;
import com.example.bookreview.MyApp;
import com.example.bookreview.R;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener,
        DBManager.DataBaseListener{

    Button moreOptions;
    Button searchBtn;
    EditText query;
    FrameLayout flhome;
    private String msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        moreOptions = v.findViewById(R.id.moreOptions);
        searchBtn = v.findViewById(R.id.searchBtn);
        query = v.findViewById(R.id.search_query);
        flhome=v.findViewById(R.id.flhome);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyApp) getActivity().getApplication()).query = query.getText().toString();
                Intent book = new Intent(getActivity().getApplication(), BooksListRecyclerView.class);
                startActivity(book);
            }
        });
        moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreOptionFunc(view);
            }
        });
        return v;
    }

    private void moreOptionFunc(View view) {
        PopupMenu popmenu=new PopupMenu(getActivity().getApplication(),view);
        popmenu.setOnMenuItemClickListener(this);
        popmenu.inflate(R.menu.main_menu);
        popmenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.resetBtn:
                resetBtnFun();
                return true;
            case R.id.recentlyReviewedBtn:
                Toast.makeText(getActivity().getApplication(),"Recently reviewed Button Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.maximumReviewedBtn:
                Toast.makeText(getActivity().getApplication(),"maximum reviewed Button Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }
    public void resetBtnFun()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure? you want to delete all data from favorites.")
                .setTitle("Reset Favorite List")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MyApp)getActivity().getApplication()).db.listener=HomeFragment.this;
                        ((MyApp)getActivity().getApplication()).db.getBookDB(getActivity().getApplicationContext());
                        ((MyApp)getActivity().getApplication()).db.deleteAllFavBook();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();

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

    }

    @Override
    public void gettingFavBooksTitleCompleted(FavBooks[] list) {

    }

    @Override
    public void deleteFavBookCompleted() {

    }

    @Override
    public void deleteFavBookWithBookIDCompleted() {

    }

    @Override
    public void deleteAllFavBookCompleted() {
        msg="Your favorite list has been reset successfully";
        Snackbar.make(flhome,
                msg,Snackbar.LENGTH_SHORT).show();
    }
}