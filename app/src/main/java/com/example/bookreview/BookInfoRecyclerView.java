package com.example.bookreview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BookInfoRecyclerView extends
        RecyclerView.Adapter<BookInfoRecyclerView.BookInfoViewHolder> {

    public BookInfoRecyclerView(ArrayList<Book> book_list,Context context) {
        this.book_list = book_list;
        this.context = context;
    }


    public ArrayList<Book> book_list;
    public ArrayList<FavBooks> favbook_list;
    Context context;
    public ItemClickListener listener;

    public interface ItemClickListener{
        public void onItemClick(int pos);
    }
    @NonNull
    @Override
    public BookInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.book_list,parent,false);
        return new BookInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookInfoViewHolder holder, int position) {
        holder.bookTitle.setText(book_list.get(position).getTitle());
      Glide.with(context).load(book_list.get(position).getThumbnail()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return book_list.size();
    }

    public class BookInfoViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        ImageView img;
        public BookInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            img = itemView.findViewById(R.id.bookImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
