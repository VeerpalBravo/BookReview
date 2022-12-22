package com.example.bookreview;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Comments.class})
public abstract class CommentsDatabase extends RoomDatabase {
    public abstract CommentsDao getDao();

}
