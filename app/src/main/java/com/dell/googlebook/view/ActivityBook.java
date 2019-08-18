package com.dell.googlebook.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dell.googlebook.DBHelper.DBFavorite;
import com.dell.googlebook.R;
import com.dell.googlebook.model.Book;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityBook extends AppCompatActivity {

    ImageButton btnFavorite;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView tvTitle, tvAuthor, tvDesc, tvCatag, tvPrice, tvInfo, tvPreview;
    CircleImageView ivThumbnail;
    RelativeLayout layoutBook;
    DBFavorite dbFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().hide();
        initView();
        dbFavorite = new DBFavorite(this);


        Bundle bundle = getIntent().getExtras();
        String title = "", author = "", description = "", category = "", publishDate = "", info = "", buy = "", preview = "", image = "", price = "", id = "";
        int pagecount = 0;

        if (bundle != null) {
            id = bundle.getString("ID");
            title = bundle.getString("Title");
            author = bundle.getString("Author");
            description = bundle.getString("Desc");
            category = bundle.getString("Category");
            publishDate = bundle.getString("Publishadte");
            info = bundle.getString("Info");
            buy = bundle.getString("Buy");
            preview = bundle.getString("Preview");
            image = bundle.getString("Imagebook");
            price = bundle.getString("Price");
            pagecount = bundle.getInt("PAGECOUNT");
        }
        collapsingToolbarLayout.setTitleEnabled(true);
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvDesc.setText(description);

        if (author == null) tvAuthor.setText("Author");
        if (category == null) tvCatag.setText("Not categories available");
        else tvCatag.setText(category);
        if (price == null) tvPrice.setText("Not_for_sale");
        else tvPrice.setText(price);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(intent);
            }
        });


        final String finalPreview = preview;
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalPreview));
                startActivity(intent);
            }
        });
        collapsingToolbarLayout.setTitle(title);
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.load_shape).error(R.drawable.load_shape);
        Glide.with(this).load(image).apply(options).into(ivThumbnail);


        //fa
        final String finalId = id;
        final String finalImage = image;
        final List<Book> books = dbFavorite.getListBook();
        final Book book = new Book(finalId, tvTitle.getText().toString(), tvAuthor.getText().toString(), tvDesc.getText().toString(), tvCatag.getText().toString(), finalImage, finalPreview, tvPrice.getText().toString(), finalInfo);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbFavorite.checkFavorite(book.getId())) {
                    btnFavorite.setImageResource(R.drawable.ic_favorite);
                    dbFavorite.deleteBook(book);
                } else {
                    btnFavorite.setImageResource(R.drawable.ic_favorite2);
                    dbFavorite.addBook(book);
                }
            }
        });

        for (int i = 0; i < books.size(); i++) {
            if (id.equals(books.get(i).getId())) {
                btnFavorite.setImageResource(R.drawable.ic_favorite2);
            }
        }
    }

    public void initView() {
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        tvTitle = findViewById(R.id.tvTitle2);
        tvAuthor = findViewById(R.id.tvAuthor2);
        tvDesc = findViewById(R.id.tvdescription);
        tvCatag = findViewById(R.id.tvCategory2);
        tvInfo = findViewById(R.id.tvInfo);
        tvPreview = findViewById(R.id.tvPreview);
        tvPrice = findViewById(R.id.tvPrice2);
        ivThumbnail = findViewById(R.id.book_image2);
        layoutBook = findViewById(R.id.layout_item_book2);
        btnFavorite = findViewById(R.id.btnFavorite);
    }
}
