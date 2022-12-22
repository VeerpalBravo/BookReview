package com.example.bookreview;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface CommentsDao {
    @Insert
    public void addComment(Comments c);

    @Delete
    public void deleteComment(Comments dc);

    @Query("select * from Comments where bookID =:bookIDForSelect ")
    Comments[] getAllComments(String bookIDForSelect);
}
