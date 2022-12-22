package com.example.bookreview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonService {
    public void getBookTitles(String stringValue, Context context){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stringValue);
            JSONArray totalBooks = jsonObject.getJSONArray("items");
            Log.d("NumberOFBooks", String.valueOf(totalBooks.length()));

            for(int i=0; i<2; i++)
            {
                JSONObject bookInfo = totalBooks.getJSONObject(i);
                JSONObject volumeInfo = bookInfo.getJSONObject("volumeInfo");
                String bookId= bookInfo.getString("id");
                JSONObject saleInfo=  bookInfo.getJSONObject("saleInfo");

                String bookTitle=volumeInfo.getString("title");
                String subTitle = volumeInfo.getString("subtitle");
                String publisher=volumeInfo.getString("publisher");
                String published_Date = volumeInfo.getString("publishedDate");
                String description = volumeInfo.getString("description");
                JSONArray authorsJson = volumeInfo.getJSONArray("authors");
                ArrayList<String> authors = new ArrayList<>();
                if (authorsJson.length() != 0) {
                    for (int j = 0; j < authorsJson.length(); j++) {
                        authors.add(authorsJson.optString(j));
                    }
                }
                int pageCount = volumeInfo.getInt("pageCount");
                String infoLink = volumeInfo.getString("infoLink");
                JSONArray categoriesJson = volumeInfo.getJSONArray("categories");
                ArrayList<String> categories = new ArrayList<>();
                if (categoriesJson.length() != 0) {
                    for (int j = 0; j < categoriesJson.length(); j++) {
                        categories.add(categoriesJson.optString(j));
                    }
                }
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String saleability=saleInfo.getString("saleability");
                String buyLink= "";
                double amount=0.0;
                JSONObject retail_price;
                String currencyCode=" ";
                if(saleability.equals("FOR_SALE"))
                {
                    buyLink= saleInfo.getString("buyLink");
                    retail_price = saleInfo.getJSONObject("retailPrice");
                    amount = retail_price.getInt("amount");
                    currencyCode = retail_price.getString("currencyCode");
                }
                String imageUrl = imageLinks.getString("thumbnail");
                ((MyApp)context.getApplicationContext()).sb.setBookList(bookTitle,imageUrl,subTitle,bookId);
                ((MyApp)context.getApplicationContext()).sb.fullDescBookList(bookTitle,subTitle,authors,
                        publisher,published_Date,description,pageCount,
                        categories,imageUrl,infoLink,buyLink,saleability,currencyCode,amount,bookId);
//                Log.d("NumberOFBooks",((MyApp)context.getApplicationContext()).sb.getBookList().get(i).getThumbnail()+" "+
//                        ((MyApp)context.getApplicationContext()).sb.getBookList().get(i).getTitle());

            }
//            Log.d("BookDescription",((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getThumbnail()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getTitle()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getSubtitle()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getAuthors().get(0)+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getPageCount()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getCategory().get(0)+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getId()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getPublishedDate()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getPublisher()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getDescription()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getAmount()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getSaleability()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getBuyLink()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getinfoLink()+" "+
//                    ((MyApp)context.getApplicationContext()).sb.getFullDescBookList().get(1).getCurrencyCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // return ((MyApp)context.getApplicationContext()).sb.getBookList();
    }
    public Bitmap convertImage(String url) {
        try {
            URL connection = new URL(url);
            InputStream in=  connection.openStream();
            Bitmap imageData = BitmapFactory.decodeStream(in);
            return imageData;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
