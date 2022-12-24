package com.example.bookreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ReviewBookActivity extends AppCompatActivity implements View.OnClickListener,
DBManager.DataBaseListener{

    ToggleButton starbtn1;
    ToggleButton starbtn2;
    ToggleButton starbtn3;
    ToggleButton starbtn4;
    ToggleButton starbtn5;
    Button save;
    Button cancel;
    TextInputEditText comment;
    String bookID;
    MaterialButtonToggleGroup toggleButtonGroup;
    int rating=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_book);
        ((MyApp)getApplication()).db.listener=this;
        ((MyApp)getApplication()).db.getDB(this);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title").replace("Title: "," ");
        bookID=intent.getStringExtra("bookID");
        setTitle(title);
        starbtn1=findViewById(R.id.star1);
        starbtn2=findViewById(R.id.star2);
        starbtn3=findViewById(R.id.star3);
        starbtn4=findViewById(R.id.star4);
        starbtn5=findViewById(R.id.star5);
        starbtn1.setOnClickListener(this);
        starbtn2.setOnClickListener(this);
        starbtn3.setOnClickListener(this);
        starbtn4.setOnClickListener(this);
        starbtn5.setOnClickListener(this);
        comment=findViewById(R.id.editText);
        cancel= findViewById(R.id.cancelBtn);
        save=findViewById(R.id.saveBtn);
        Log.d("valueRating", rating+"onCreate");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("reviewVaues: ",rating+ String.valueOf(comment.getText().toString().isEmpty()));
                String msg = "";
                if(rating==0 || comment.getText().toString().isEmpty()){
                    Log.d("reviewVaues: ",rating+ String.valueOf(comment.getText().toString().isEmpty()));
                    msg="Please enter reviews and select atleast one star for rating";
                }
                else {
                    ((MyApp) getApplication()).db.insertNewCommentAsync(new Comments(bookID, rating, comment.getText().toString()));
                    msg = "your feedback is submitted successfully.";
                }
                showAlert(msg);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToPerfomFuncActivity = new Intent(ReviewBookActivity.this,
                        PerformFunctionOnSelectedBookActivity.class);
                startActivity(backToPerfomFuncActivity);
            }
        });

    }
    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    public void showAlert(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReviewBookActivity.this);
        builder.setMessage(msg).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.star1:
                if(starbtn1.isChecked()){
                rating=1;
                    starbtn1.setClickable(false);
                    starbtn2.setClickable(false);
                    starbtn3.setClickable(false);
                    starbtn4.setClickable(false);
                    starbtn5.setClickable(false);}
                break;
            case R.id.star2:
                if(starbtn2.isChecked()){
                rating=2;
                    starbtn1.setChecked(true);
                    starbtn1.setClickable(false);
                    starbtn2.setClickable(false);
                    starbtn3.setClickable(false);
                    starbtn4.setClickable(false);
                    starbtn5.setClickable(false);
                }
                break;
            case R.id.star3:
                if(starbtn3.isChecked()){
                rating=3;
                    starbtn1.setChecked(true);
                    starbtn2.setChecked(true);
                    starbtn1.setClickable(false);
                    starbtn2.setClickable(false);
                    starbtn3.setClickable(false);
                    starbtn4.setClickable(false);
                    starbtn5.setClickable(false);
                    }
                break;
            case R.id.star4:
                if(starbtn4.isChecked()){
                    rating=4;
                    starbtn1.setChecked(true);
                    starbtn2.setChecked(true);
                    starbtn3.setChecked(true);
                    starbtn1.setClickable(false);
                    starbtn2.setClickable(false);
                    starbtn3.setClickable(false);
                    starbtn4.setClickable(false);
                    starbtn5.setClickable(false);}
                break;
            case R.id.star5:
                if(starbtn5.isChecked()) {
                    rating = 5;
                    starbtn1.setChecked(true);
                    starbtn2.setChecked(true);
                    starbtn3.setChecked(true);
                    starbtn4.setChecked(true);
                    starbtn1.setClickable(false);
                    starbtn2.setClickable(false);
                    starbtn3.setClickable(false);
                    starbtn4.setClickable(false);
                    starbtn5.setClickable(false);
                }
                break;
            default:
                break;

        }
        Toast.makeText(this,"rating value: "+rating, Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rating", rating);
        Log.d("valueRating", rating+"savedState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rating = savedInstanceState.getInt("rating",0);
        Log.d("valueRating", rating+"savedRestoreState");
    }
}