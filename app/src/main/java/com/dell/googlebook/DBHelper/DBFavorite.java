package com.dell.googlebook.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dell.googlebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DBFavorite extends SQLiteOpenHelper {

    public static final String DB_NAME = "GoogleBook";
    public static final String TABLE_NAME = "Book";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String IMAGEBOOK = "imagebook";
    public static final String PREVIEW = "preview";
    public static final String PRICE = "price";
    public static final String INFO = "info";

    public static final String CREATE_TABLEBOOK = "create table " + TABLE_NAME + "( " +
            ID + " text primary key, " +
            TITLE + " text, " +
            AUTHOR + " text, " +
            DESCRIPTION + " text, " +
            CATEGORY + " text, " +
            IMAGEBOOK + " text, " +
            PREVIEW + " text, " +
            PRICE + " text, " +
            INFO + " text " +
            ")";

    private Context context;

    public DBFavorite(Context context) {
        super(context, DB_NAME, null, 1);
        Log.e("DATABASE", "Success");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLEBOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(Book book) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, book.getId());
        values.put(TITLE, book.getTitle());
        values.put(AUTHOR, book.getAuthor());
        values.put(DESCRIPTION, book.getDescription());
        values.put(CATEGORY, book.getCategory());
        values.put(IMAGEBOOK, book.getImageBook());
        values.put(PREVIEW, book.getPreview());
        values.put(PRICE, book.getPrice());
        values.put(INFO, book.getInfo());

        database.insert(TABLE_NAME, null, values);
        database.close();
        Log.e("ADD", "success");
    }

    public List<Book> getListBook() {
        List<Book> books = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getString(cursor.getColumnIndex(ID)));
                book.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                book.setAuthor(cursor.getString(cursor.getColumnIndex(AUTHOR)));
                book.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                book.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                book.setImageBook(cursor.getString(cursor.getColumnIndex(IMAGEBOOK)));
                book.setPreview(cursor.getString(cursor.getColumnIndex(PREVIEW)));
                book.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
                book.setInfo(cursor.getString(cursor.getColumnIndex(INFO)));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return books;
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(book.getId())});
        Log.e("DELETE", "DELETE");
        db.close();
    }

    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, TITLE, AUTHOR, DESCRIPTION, CATEGORY, IMAGEBOOK, PREVIEW, PRICE, INFO}, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        assert cursor != null;
        Book note = new Book(cursor.getString(cursor.getColumnIndex(ID)), cursor.getString(cursor.getColumnIndex(TITLE)), cursor.getString(cursor.getColumnIndex(AUTHOR)), cursor.getString(cursor.getColumnIndex(DESCRIPTION)), cursor.getString(cursor.getColumnIndex(CATEGORY)), cursor.getString(cursor.getColumnIndex(IMAGEBOOK)), cursor.getString(cursor.getColumnIndex(PREVIEW)), cursor.getString(cursor.getColumnIndex(PRICE)), cursor.getString(cursor.getColumnIndex(INFO)));
        cursor.close();
        db.close();
        return note;
    }

    public boolean checkFavorite(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
