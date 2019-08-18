package com.dell.googlebook.model;

public class ListBook {

    private String imageList;
    private String listName;

    public ListBook(String imageList, String listName) {
        this.imageList = imageList;
        this.listName = listName;
    }
    public ListBook(){}

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
