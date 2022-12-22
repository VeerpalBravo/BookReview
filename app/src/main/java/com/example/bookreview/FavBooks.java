package com.example.bookreview;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavBooks {
    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    String thumbnail;
    String bookID;
    String query;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getBookID() {
        return bookID;
    }

    public String getQuery() {
        return query;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public FavBooks(String title, String thumbnail, String bookID, String query) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.bookID = bookID;
        this.query = query;
    }
}
