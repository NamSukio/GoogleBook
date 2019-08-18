package com.dell.googlebook.presenter;

import com.dell.googlebook.model.Item;

import java.util.List;

public interface LoadDataBook {

    void parseJson(List<Item> books);
    void loadFailer(String mess);
    void searchSuccess(List<Item> list_search);
}
