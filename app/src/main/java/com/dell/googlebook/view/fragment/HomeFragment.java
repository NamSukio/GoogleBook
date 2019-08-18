package com.dell.googlebook.view.fragment;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.googlebook.R;
import com.dell.googlebook.adapter.BookAdapter;
import com.dell.googlebook.model.Item;
import com.dell.googlebook.presenter.LoadDataBook;
import com.dell.googlebook.presenter.MainPresenter;

import java.util.List;

public class HomeFragment extends Fragment implements LoadDataBook,SearchView.OnQueryTextListener{


    TextView tvFailed;
    RecyclerView rcBook;
    SearchView searchView;
    BookAdapter bookAdapter;
    MainPresenter mainPresenter;
    String url = "https://www.googleapis.com/books/v1/volumes?q=intitle";
    String urlQuery = "https://www.googleapis.com/books/v1/volumes?q=";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        rcBook = view.findViewById(R.id.recycleview_book);
        tvFailed = view.findViewById(R.id.tvFailed);
        //recycleview
        rcBook.setHasFixedSize(true);
        rcBook.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainPresenter.ConnectionFailed(getContext());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getDataToUrl(getContext(),url);

    }

    @Override
    public void parseJson(List<Item> books) {
        bookAdapter = new BookAdapter(getContext(), books);
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
        bookAdapter = new BookAdapter(getContext(), list_search);
        rcBook.setAdapter(bookAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_bar,menu);

        searchView = (SearchView) menu.findItem(R.id.searchmenu).getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.equals("")) {
            Toast.makeText(getContext(), "Please enter your query", Toast.LENGTH_LONG).show();
            return false;
        }
        mainPresenter.searchBook(getContext(), urlQuery + s);
        bookAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

}
