package com.example.bookreview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comments implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String bookID;

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBookID() {
        return bookID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    int rating;
    String comment;

    public Comments(String bookID, int rating, String comment) {
        this.bookID = bookID;
        this.rating = rating;
        this.comment = comment;
    }


    protected Comments(Parcel in) {
        id = in.readInt();
        bookID = in.readString();
        rating = in.readInt();
        comment = in.readString();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(bookID);
        parcel.writeInt(rating);
        parcel.writeString(comment);
    }
}
