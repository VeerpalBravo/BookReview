package com.example.bookreview;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;

import java.util.ArrayList;

public class Book implements Parcelable {
            // creating string, int and array list
        // variables for our book details

        private int id;
        private String title;
        private String subtitle;
        private ArrayList<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private int pageCount;
        private ArrayList<String> category;
        private String thumbnail;
        private String previewLink;
        private String infoLink;
        private String buyLink;
        private String saleability;
        private String currencyCode;
        private double amount;
        private String bookid;
        int favIcon;

    public int getFavIcon() {
        return favIcon;
    }

    public void setFavIcon(int favIcon) {
        this.favIcon = favIcon;
    }

    public Book(String title, String subtitle, ArrayList<String> authors, String publisher,
                String publishedDate, String description, int pageCount, ArrayList<String> category,
                String thumbnail, String infoLink, String buyLink, String saleability,
                String currencyCode, double amount, String id, int favIcon) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.category = category;
        this.thumbnail = thumbnail;
        this.infoLink = infoLink;
        this.buyLink = buyLink;
        this.saleability = saleability;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.bookid = id;
        this.favIcon=favIcon;
    }

    protected Book(Parcel in) {
        id=in.readInt();
        bookid = in.readString();
        title = in.readString();
        subtitle = in.readString();
        authors = in.createStringArrayList();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        category = in.createStringArrayList();
        thumbnail = in.readString();
        previewLink = in.readString();
        infoLink = in.readString();
        buyLink = in.readString();
        saleability = in.readString();
        currencyCode = in.readString();
        amount = in.readDouble();

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    // creating getter and setter methods

    public Book(String title, String subtitle, ArrayList<String> authors,
                String publisher, String publishedDate, String description,
                int pageCount, String thumbnail, String previewLink, String infoLink,
                String buyLink) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.buyLink = buyLink;
    }

    public Book(String title, String img, String subtitle, String bookId) {
        this.title=title;
        this.thumbnail=img;
        this.subtitle=subtitle;
        this.bookid=bookId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public String getSaleability() {
        return saleability;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public String getBookid() {
        return bookid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public Book(String title, String thumbnail){
            this.title = title;
            this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(bookid);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeStringList(authors);
        parcel.writeString(publisher);
        parcel.writeString(publishedDate);
        parcel.writeString(description);
        parcel.writeInt(pageCount);
        parcel.writeStringList(category);
        parcel.writeString(thumbnail);
        parcel.writeString(previewLink);
        parcel.writeString(infoLink);
        parcel.writeString(buyLink);
        parcel.writeString(saleability);
        parcel.writeString(currencyCode);
        parcel.writeDouble(amount);
    }
}
