package com.dell.googlebook.model;

public class Book {


    private String title;
    private String author;
    private String publishedDate;
    private String description;
    private String category;
    private String imageBook;
    private String retailPrice;
    private String buy;
    private String preview;
    private String price;
    private int pageCount;
    private String url;

    public Book(String title, String author, String publishedDate, String description, String category, String imageBook, String buy, String preview, String price, int pageCount, String url) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.description = description;
        this.category = category;
        this.imageBook = imageBook;
        this.buy = buy;
        this.preview = preview;
        this.price = price;
        this.pageCount = pageCount;
        this.url = url;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
