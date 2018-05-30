package com.example.englishplay.bookrecycle.adopter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishplay.bookrecycle.MainActivity;
import com.example.englishplay.bookrecycle.R;
import com.example.englishplay.bookrecycle.bean.Book;
import com.example.englishplay.bookrecycle.buy_sell.BuyActivity;

import java.util.List;

public class BookAdopter extends RecyclerView.Adapter<BookAdopter.ViewHolder> {

    public BookAdopter(List<Book> bookList){
        this.bookList=bookList;
    }

    private List<Book> bookList;
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
//        holder.bookView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "bookView", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.bookImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "bookImageView", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.bookName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "bookTextView", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.recyclerView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book=bookList.get(position);
                Intent toBuy=new Intent(parent.getContext(), BuyActivity.class);
                toBuy.putExtra("book",book.getName());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book=bookList.get(position);
        holder.bookName.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView1;
        View bookView;
        TextView bookName;
        ImageView bookImageView;
        public ViewHolder(View itemView) {
            super(itemView);
//            bookView=itemView;
//            bookImageView=itemView.findViewById(R.id.book_item_ImageView);
//            bookName=itemView.findViewById(R.id.book_item_TextView);
            recyclerView1=itemView.findViewById(R.id.one_recycleView);
        }
    }
}
