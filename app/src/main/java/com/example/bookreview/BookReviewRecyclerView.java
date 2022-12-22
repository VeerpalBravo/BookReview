package com.example.bookreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookReviewRecyclerView extends
        RecyclerView.Adapter<BookReviewRecyclerView.BookReviewViewHolder> {

    ArrayList<Comments> comment_list=new ArrayList<>(0);
    Context context;
    BookReviewRecyclerView.ItemClickListener listener;

    public BookReviewRecyclerView(ArrayList<Comments> comment_list, Context context) {
        this.comment_list = comment_list;
        this.context = context;
    }


    interface ItemClickListener{
        public void onItemClick(int pos);
    }
    @NonNull
    @Override
    public BookReviewRecyclerView.BookReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.comment_list,parent,false);
        return new BookReviewRecyclerView.BookReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookReviewRecyclerView.BookReviewViewHolder holder, int position) {
        holder.comment.setText(comment_list.get(position).getComment());
        String rating = comment_list.get(position).getRating()+" star";
        holder.rating.setText(rating);

    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public class BookReviewViewHolder extends RecyclerView.ViewHolder{
        TextView comment;
        TextView rating;
        public BookReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            rating = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
