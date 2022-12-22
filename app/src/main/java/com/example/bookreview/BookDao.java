package com.example.bookreview;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BookDao {
    @Insert
    public void insertFavBook(FavBooks book);
    @Query("Select * from FavBooks")
    public FavBooks[] getAllFavBooks();
    @Query("Select id,title from FavBooks")
    public FavBooks[] getTitleFavBooks();
}