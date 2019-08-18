package com.dell.googlebook.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dell.googlebook.DBHelper.DBFavorite;
import com.dell.googlebook.R;
import com.dell.googlebook.adapter.BookAdapter;
import com.dell.googlebook.adapter.FavoriteAdapter;
import com.dell.googlebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    RecyclerView rcBookFavorite;
    FavoriteAdapter favoriteAdapter;
    List<Book> bookList;
    DBFavorite dbFavorite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fravorite, container, false);

        rcBookFavorite = view.findViewById(R.id.rcBookFavorite);
        favoriteAdapter = new FavoriteAdapter(getContext(), bookList);
        rcBookFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcBookFavorite.setAdapter(favoriteAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        dbFavorite = new DBFavorite(getActivity());
        bookList = dbFavorite.getListBook();
        dbFavorite.close();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.e("RESUME","resume");
        dbFavorite = new DBFavorite(getActivity());
        bookList = dbFavorite.getListBook();
        favoriteAdapter = new FavoriteAdapter(getContext(),bookList);
        rcBookFavorite.setAdapter(favoriteAdapter);
        super.onResume();
    }

    public void updateList() {
        favoriteAdapter.notifyDataSetChanged();
    }
}
