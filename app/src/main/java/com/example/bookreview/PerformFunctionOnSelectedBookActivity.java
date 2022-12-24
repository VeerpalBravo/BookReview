package com.example.bookreview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class PerformFunctionOnSelectedBookActivity extends AppCompatActivity implements
        DBManager.DataBaseListener{
    TextView hyperTextLink;
    TextView title;
    TextView subtitle;
    TextView author;
    TextView amount;
    TextView publisher;
    TextView publishedDate;
    TextView category;
    TextView description;
    ImageView img;
    Button buyBtn;
    Button reviewbtn;
    Button bookRatingBtn;
    String bookID;
    ToggleButton favorite;


    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_function_on_selected_book);
        setTitle("Book Description");
        position =((MyApp)getApplication()).pos;
        title=findViewById(R.id.titlePerformFunc);
        subtitle=findViewById(R.id.subtitlePerformFunc);
        author=findViewById(R.id.authorPerformFunc);
        category=findViewById(R.id.categoryPerformFunc);
        description=findViewById(R.id.descriptionPerformFunc);
        description.setMovementMethod(new ScrollingMovementMethod());
        amount=findViewById(R.id.pricePerformFunc);
        publisher=findViewById(R.id.publisherPerformFunc);
        publishedDate=findViewById(R.id.publishDatePerformFunc);
        img=findViewById(R.id.imgPerformFunc);
        buyBtn=findViewById(R.id.buyThisBookBtn);
        reviewbtn=findViewById(R.id.ReviewCommentsBtn);
        bookRatingBtn=findViewById(R.id.RateBookBtn);
        favorite=findViewById(R.id.favoriteIcon);
        hyperTextLink=findViewById(R.id.hyperTextLinkPerformFunc);
        bookRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reviewActivityLink = new Intent(PerformFunctionOnSelectedBookActivity.this,
                        ReviewBookActivity.class);
                reviewActivityLink.putExtra("title",title.getText().toString());
                reviewActivityLink.putExtra("bookID",bookID);
                startActivity(reviewActivityLink);
            }
        });
        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookComments = new Intent(PerformFunctionOnSelectedBookActivity.this,
                        BookReviewCommentsActivity.class);
                startActivity(bookComments);
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favToggleBtnFunc();
            }
        });
    }

    public void showBookDescription(int pos){
        String linkUrl=((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getInfoLink();
        String url =String.format("<a href=\"%s\">Click here for more info:"+linkUrl+"</a> ", linkUrl);
        Log.d("url",url);
        String titleV="<b>Title: </b>";
        String titleFromJson=((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getTitle();
        bookID=((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getBookid();
        title.setText(Html.fromHtml(titleV)+titleFromJson);
        title.setTextColor(getResources().getColor(R.color.purple_500));
        subtitle.setText("Subtitle: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getSubtitle());
        subtitle.setTextColor(getResources().getColor(R.color.purple_200));
        publisher.setText("Publisher name: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getPublisher());
        publisher.setTextColor(getResources().getColor(R.color.black));
        publishedDate.setTextColor(getResources().getColor(R.color.black));
        publishedDate.setText("Published on: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getPublishedDate());
        hyperTextLink.setText(Html.fromHtml(url));
        Log.d("url",hyperTextLink.getText().toString());
        hyperTextLink.setTextColor(getResources().getColor(R.color.purple_500));
        hyperTextLink.setMovementMethod(LinkMovementMethod.getInstance());
        description.setTextColor(getResources().getColor(R.color.black));
        description.setText("Description Of Book: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getDescription());
        if(((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getSaleability().equals("FOR_SALE")) {
            amount.setText("Price of Book: "+((MyApp) getApplication()).sb.getFullDescBookList().get(pos).getAmount() +
                    ((MyApp) getApplication()).sb.getFullDescBookList().get(pos).getCurrencyCode());
            amount.setTextColor(getResources().getColor(R.color.black));
        }
        else
        {
            amount.setText("Not for Sale");
        }
        int categorySize=((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getCategory().size();
        for(int i=0;i<categorySize;i++){
            category.setTextColor(getResources().getColor(R.color.Coral));
            category.setText("Genre: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getCategory().get(i));
        }
        int No_of_authors=((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getAuthors().size();
        for(int i=0;i<No_of_authors;i++){
            author.setTextColor(getResources().getColor(R.color.Red));
            author.setText("Author: "+((MyApp)getApplication()).sb.getFullDescBookList().get(pos).getAuthors().get(i));
        }
        Glide.with(this).load(((MyApp)getApplication()).sb.getFullDescBookList().get(pos).
                getThumbnail()).into(img);
        System.out.println(((MyApp)getApplication()).sb.getBookIDList());
        if(((MyApp)getApplication()).sb.getBookIDList().contains(bookID)){
                if(((MyApp)getApplication()).sb.getBookIDList().contains(bookID)){
                    favorite.setChecked(true);
                    ((MyApp)getApplication()).toggleBtn=1;
                }
                else{
                    favorite.setChecked(false);
                    ((MyApp)getApplication()).toggleBtn=0;
                }
        }

    }


    public void favToggleBtnFunc(){
        if(favorite.isChecked()){
            ((MyApp)getApplication()).toggleBtn=1;
            int pos=((MyApp)getApplication()).pos;
            ((MyApp)getApplication()).db.getTitleFavBooks();
            if(((MyApp)getApplication()).sb.getBookIDList().contains(bookID)){
                Toast.makeText(PerformFunctionOnSelectedBookActivity.this,
                        "This is already in favorite list.",Toast.LENGTH_SHORT).show();

            }
            else {
                ((MyApp) getApplication()).db.insertNewFavBookAsync(new
                        FavBooks(((MyApp) getApplication()).sb.getFullDescBookList().get(pos).getTitle(),
                        ((MyApp) getApplication()).sb.getFullDescBookList().get(pos).getThumbnail(),
                        ((MyApp) getApplication()).sb.getFullDescBookList().get(pos).getBookid(),
                        ((MyApp) getApplication()).query));
                AlertDialog.Builder builder = new AlertDialog.Builder(PerformFunctionOnSelectedBookActivity.this);
                builder.setMessage("Your book saved in favorite list.").show();
            }
        }
        else{
            ((MyApp)getApplication()).db.deleteFavBookWithBookID(bookID);
            ((MyApp)getApplication()).toggleBtn=0;;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyApp)getApplication()).db.listener=this;
        ((MyApp)getApplication()).db.getBookDB(this);
        //toggleButtonFunc();
        showBookDescription(position);
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


}