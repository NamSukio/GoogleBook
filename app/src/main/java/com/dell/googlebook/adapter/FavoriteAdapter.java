package com.dell.googlebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dell.googlebook.R;
import com.dell.googlebook.model.Book;
import com.dell.googlebook.view.ActivityBook;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    FragmentManager fm;
    Context context;
    List<Book> bookList;
    public FavoriteAdapter(Context context, List<Book> bookList){
        this.context = context;
        this.bookList = bookList;
    }
    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_book,viewGroup,false);
        final FavoriteHolder holder = new FavoriteHolder(view);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityBook.class);
                int pos = holder.getAdapterPosition();
                intent.putExtra("ID",bookList.get(pos).getId());
                intent.putExtra("Title",bookList.get(pos).getTitle());
                intent.putExtra("Author",bookList.get(pos).getAuthor());
                intent.putExtra("Desc",bookList.get(pos).getDescription());
                intent.putExtra("Category",bookList.get(pos).getCategory());
                intent.putExtra("Price",bookList.get(pos).getPrice());
                intent.putExtra("Info",bookList.get(pos).getInfo());
                intent.putExtra("Preview",bookList.get(pos).getPreview());
                intent.putExtra("Imagebook",bookList.get(pos).getImageBook());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder favoriteHolder, int i) {
        Book book = bookList.get(i);
        try {
            favoriteHolder.tvTitle.setText(book.getTitle());
            favoriteHolder.tvAuthor.setText(book.getAuthor());
            favoriteHolder.tvPrice.setText(book.getPrice());
            favoriteHolder.tvCategory.setText(book.getCategory());
        }catch (NullPointerException e){

        }
        Glide.with(context).load(book.getImageBook()).into(favoriteHolder.imageBook);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        CircleImageView imageBook;
        TextView tvTitle, tvCategory, tvPrice, tvAuthor;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_book);
            imageBook = itemView.findViewById(R.id.imagebook1);
            tvTitle = itemView.findViewById(R.id.tvTitle1);
            tvCategory = itemView.findViewById(R.id.tvCategory1);
            tvPrice = itemView.findViewById(R.id.tvPrice1);
            tvAuthor = itemView.findViewById(R.id.tvAuthor1);
        }
    }
}
