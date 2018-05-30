package com.example.englishplay.bookrecycle.adopter;

import android.content.Context;
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
import com.example.englishplay.bookrecycle.tools.OnItemClickListener;

import java.util.List;

public class BookAdopter extends RecyclerView.Adapter<BookAdopter.ViewHolder> {
    private List<Book> bookList;

    public BookAdopter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        final MainActivity mainActivity = new MainActivity();
        final ViewHolder holder = new ViewHolder(view, mainActivity.context);
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
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bookName.setText(book.getName());


//        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);

        View itemView =holder.itemView;
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookName;
//        RelativeLayout bookItem;
//        View bookView;
//        ImageView bookImageView;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
//            bookView=itemView;
//            bookImageView=itemView.findViewById(R.id.book_item_ImageView);
//            bookItem =itemView.findViewById(R.id.book_item);
            bookName = itemView.findViewById(R.id.book_item_TextView);
        }
    }
}
