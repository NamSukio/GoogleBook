package com.dell.googlebook.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dell.googlebook.model.Book;
import com.dell.googlebook.model.BookResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainPresenter  {
    LoadDataBook loadDataBook;


    public MainPresenter(LoadDataBook loadDataBook){
        this.loadDataBook = loadDataBook;
    }

    //List book
    public void getDataToUrl(Context context,String url){
        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                BookResponse bookResponse = gson.fromJson(response, BookResponse.class);
                loadDataBook.parseJson(bookResponse.getListItem());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void searchBook(Context context, String search_query){
        final StringRequest request = new StringRequest(Request.Method.GET, search_query, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                BookResponse bookResponse = gson.fromJson(response, BookResponse.class);

                loadDataBook.searchSuccess(bookResponse.getListItem());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public boolean read_connection(Context context){
        boolean isConnect;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        isConnect = info!=null&&info.isConnectedOrConnecting();
        return isConnect;
    }

    public void ConnectionFailed(Context context){
        if(!read_connection(context)){
            loadDataBook.loadFailer("Failed get data !!! \nPlease check the connection !!!");
        }
    }

}
