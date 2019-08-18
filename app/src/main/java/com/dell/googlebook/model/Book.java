package com.dell.googlebook.model;

public class Book {

    private int idList;
    private String id;
    private String title;
    private String author;
    private String description;
    private String category;
    private String imageBook;
    private String preview;
    private String price;
    private String info;
    boolean checkbox;

    public Book(String id, String title, String author, String description, String category, String imageBook, String preview, String price, String info) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.imageBook = imageBook;
        this.preview = preview;
        this.price = price;
        this.info = info;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Book(int idList, String id, String title, String author, String description, String category, String imageBook, String preview, String price, String info) {
        this.idList = idList;
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.imageBook = imageBook;
        this.preview = preview;
        this.price = price;
        this.info = info;
    }
}
