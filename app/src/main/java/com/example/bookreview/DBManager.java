package com.example.bookreview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;

public class DBManager {
    public interface DataBaseListener{
        void insertingCommentsCompleted();
        void gettingCommentsCompleted(Comments[] list);
        void insertingBooksCompleted();
        void gettingFavBooksCompleted(FavBooks[] list);
        void gettingFavBooksTitleCompleted(FavBooks[] list);
        void deleteFavBookCompleted();
        void deleteFavBookWithBookIDCompleted();
        void deleteAllFavBookCompleted();
    }

    public DataBaseListener listener;

    CommentsDatabase commentsDB;
    BookDatabase bookDB;
    Handler dbHandler = new Handler(Looper.getMainLooper());

    CommentsDatabase getDB(Context context){
        if (commentsDB == null)
            commentsDB = Room.databaseBuilder(context,CommentsDatabase.class,"comments_db").build();

        return commentsDB;
    }
    public BookDatabase getBookDB(Context context){
        if (bookDB == null)
            bookDB = Room.databaseBuilder(context,BookDatabase.class,"book_db").build();

        return bookDB;
    }

    void insertNewCommentAsync(Comments comments){

        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                commentsDB.getDao().addComment(comments);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.insertingCommentsCompleted();
                    }
                });
            }
        });
    }

    void insertNewFavBookAsync(FavBooks book){

        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookDB.getDao().insertFavBook(book);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.insertingBooksCompleted();
                    }
                });
            }
        });
    }

    void getAllComments(String bookID){
        MyApp.executorService.execute(new Runnable() {
            Comments[] list;
            @Override
            public void run() {
                Log.d("bookID: ",bookID);
                list = commentsDB.getDao().getAllComments(bookID);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.gettingCommentsCompleted(list);
                    }
                });
            }
        });
    }

    public void getAllFavBooks() {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                FavBooks[] list = bookDB.getDao().getAllFavBooks();
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.gettingFavBooksCompleted(list);
                    }
                });
            }
        });
    }
    public void getTitleFavBooks() {
        MyApp.executorService.execute(new Runnable() {
            FavBooks[] list;
            @Override
            public void run() {
                try {
                  list = bookDB.getDao().getTitleFavBooks();
                }
                catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.gettingFavBooksTitleCompleted(list);
                    }
                });
            }
        });
    }
    public void deleteFavBook(FavBooks book) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDB.getDao().deleteFavBook(book);
                }
                catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.deleteFavBookCompleted();
                    }
                });
            }
        });
    }
    public void deleteFavBookWithBookID(String book) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDB.getDao().deleteFavBook(book);
                } catch (Exception e) {

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.deleteFavBookWithBookIDCompleted();
                    }
                });
            }
        });
    }
    public void deleteAllFavBook() {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDB.getDao().resetFavBook();
                } catch (Exception e) {

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.deleteAllFavBookCompleted();
                    }
                });
            }
        });
    }
}
