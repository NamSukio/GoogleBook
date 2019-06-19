package com.dell.googlebook.view;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.googlebook.R;
import com.dell.googlebook.adapter.BookAdapter;
import com.dell.googlebook.model.Book;
import com.dell.googlebook.model.BookResponse;
import com.dell.googlebook.model.Item;
import com.dell.googlebook.model.LoadDataBook;
import com.dell.googlebook.presenter.MainPresenter;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LoadDataBook {

    DrawerLayout mDrawerLayout;
    android.support.v7.app.ActionBarDrawerToggle mToggle;
    SearchView searchView;
    TextView tvFailed;
    MainPresenter mainPresenter;

    RecyclerView rcBook;
    BookAdapter bookAdapter;
    BookResponse bookResponse;
    List<Item> itemList;

    String url = "https://www.googleapis.com/books/v1/volumes?q=intitle";
    String urlQuery = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.main_acctivity);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //axa
        tvFailed = findViewById(R.id.tvFailed);
        rcBook = findViewById(R.id.recycleview_book);

        rcBook.setHasFixedSize(true);
        rcBook.setLayoutManager(new LinearLayoutManager(this));

        mainPresenter = new MainPresenter(this);
        mainPresenter.getDataToUrl(this,url);
        mainPresenter.ConnectionFailed(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        MenuItem item = menu.findItem(R.id.searchmenu);
        searchView = (SearchView) item.getActionView();
        setupSearchView(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setIconifiedByDefault(true);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for(SearchableInfo inf : searchables) {
                if(inf.getSuggestAuthority() != null && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            searchView.setSearchableInfo(info);
        }
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(s.equals("")){
            Toast.makeText(this, "Please enter your query", Toast.LENGTH_LONG).show();
            return false;
        }
        mainPresenter.searchBook(this,urlQuery+s);
        bookAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void parseJsonTest(String [] key) {
        for (int i = 0; i<key.length; i++){
            tvFailed.setText(key[i]+" \n");
        }
        tvFailed.setVisibility(View.VISIBLE);
    }


    @Override
    public void parseJson(List<Item> books) {
        bookAdapter = new BookAdapter(this,books);
        rcBook.setAdapter(bookAdapter);
    }

    @Override
    public void loadFailer(String mess) {
        tvFailed.setText(mess);
        tvFailed.setVisibility(View.VISIBLE);
        rcBook.setVisibility(View.INVISIBLE);
    }

    @Override
    public void searchSuccess(List<Item> list_search) {
        bookAdapter = new BookAdapter(this,list_search);
        rcBook.setAdapter(bookAdapter);
    }
}
