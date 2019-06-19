package com.dell.googlebook.model;

import java.util.List;

public interface LoadDataBook {
    void parseJsonTest(String [] key);

    void parseJson(List<Item> books);
    void loadFailer(String mess);
    void searchSuccess(List<Item> list_search);
}
