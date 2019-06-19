package com.dell.googlebook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SaleInfo implements Serializable {


    @SerializedName("listPrice")
    @Expose
    private ListPrice listPrice;
    @SerializedName("buyLink")
    @Expose
    private String buyLink;

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public SaleInfo(ListPrice listPrice, String buyLink) {
        this.listPrice = listPrice;
        this.buyLink = buyLink;
    }
}

