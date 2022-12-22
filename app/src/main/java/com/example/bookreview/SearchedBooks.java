package com.example.bookreview;

import android.graphics.Bitmap;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchedBooks {

    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<Book> fullDescBookList = new ArrayList<>();
    public void setBookList(String title, String img, String subtitle, String bookId){
        bookList.add(new Book(title,img, subtitle, bookId));
    }
    public ArrayList<Book> getBookList(){
        return bookList;
    }


    public void fullDescBookList(String bookTitle, String subTitle, ArrayList<String> authors,
                                 String publisher, String published_date, String description, int pageCount,
                                 ArrayList<String> categories, String imageUrl, String infoLink, String buyLink,
                                 String saleability, String currencyCode, double amount, String bookId) {
        fullDescBookList.add(new Book(bookTitle,subTitle,authors,publisher,published_date,description,pageCount,
                categories,imageUrl,infoLink,buyLink,saleability,currencyCode,amount,bookId));
    }
    public ArrayList<Book> getFullDescBookList(){
        return fullDescBookList;
    }

//
}
