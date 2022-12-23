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

public class FavBookRecyclerView extends
        RecyclerView.Adapter<FavBookRecyclerView.FavBookInfoViewHolder> {

    public FavBookRecyclerView(ArrayList<FavBooks> book_list, Context context) {
        this.favbook_list = book_list;
        this.context = context;
    }

    public ArrayList<FavBooks> favbook_list;
    Context context;
    public FavBookRecyclerView.ItemClickListener listener;

    public interface ItemClickListener{
        public void onItemClick(int pos);
    }
    @NonNull
    @Override
    public FavBookRecyclerView.FavBookInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.book_list,parent,false);
        return new FavBookRecyclerView.FavBookInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavBookRecyclerView.FavBookInfoViewHolder holder, int position) {
        holder.bookTitle.setText(favbook_list.get(position).getTitle());
        Glide.with(context).load(favbook_list.get(position).getThumbnail()).into(holder.img);

    }
    public FavBooks getFavbooksPosition(int position){
        return favbook_list.get(position);
    }

    @Override
    public int getItemCount() {
        return favbook_list.size();
    }

    public class FavBookInfoViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        ImageView img;
        public FavBookInfoViewHolder(@NonNull View itemView) {
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
