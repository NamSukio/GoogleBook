package com.dell.googlebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dell.googlebook.R;
import com.dell.googlebook.model.Book;
import com.dell.googlebook.model.Item;
import com.dell.googlebook.view.ActivityBook;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    Context context;
    RequestOptions options;
    List<Item> items;
    public BookAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.load_shape).error(R.drawable.load_shape);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_book,viewGroup,false);
        final BookHolder holder = new BookHolder(view);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityBook.class);
                int pos = holder.getAdapterPosition();
                intent.putExtra("ID",items.get(pos).getId());
                intent.putExtra("Title",items.get(pos).getVolumeInfo().getTitle());

                intent.putExtra("Desc",items.get(pos).getVolumeInfo().getDescription());
                try {
                    intent.putExtra("Author",items.get(pos).getVolumeInfo().getAuthors().toString());
                    intent.putExtra("Category",items.get(pos).getVolumeInfo().getCategories().get(0));
                    intent.putExtra("Price",items.get(pos).getSaleInfo().getListPrice().toString());
                }catch (NullPointerException e){

                }
                intent.putExtra("PublishDate",items.get(pos).getVolumeInfo().getPublishedDate());
                intent.putExtra("Info",items.get(pos).getVolumeInfo().getInfoLink());
                intent.putExtra("Buy",items.get(pos).getSaleInfo().getBuyLink());
                intent.putExtra("Preview",items.get(pos).getVolumeInfo().getPreviewLink());
                intent.putExtra("Imagebook",items.get(pos).getVolumeInfo().getImageLinks().getThumbnail());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        Item book = items.get(i);
        try {
            bookHolder.tvTitle.setText(book.getVolumeInfo().getTitle());
            bookHolder.tvAuthor.setText(book.getVolumeInfo().getAuthors().get(0));
            String price =book.getSaleInfo().getListPrice().toString(),category =book.getVolumeInfo().getCategories().get(0);
            bookHolder.tvPrice.setText(price);
            bookHolder.tvCategory.setText(category);
        }catch (NullPointerException e){

        }
        if(book.getVolumeInfo().getImageLinks().getThumbnail() == null){
            Glide.with(context).load(R.drawable.bookbackground).into(bookHolder.imageBook);
        }else {
            Glide.with(context).load(book.getVolumeInfo().getImageLinks().getThumbnail()).placeholder(R.drawable
                    .bookbackground).error(R.drawable.bookbackground)
                    .into(bookHolder.imageBook);
        }
        //Glide.with(context).load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(bookHolder.imageBook);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutItem;
        CircleImageView imageBook;
        TextView tvTitle, tvCategory, tvPrice, tvAuthor;
        public BookHolder(@NonNull View itemView) {
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
