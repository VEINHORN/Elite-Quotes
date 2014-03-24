package com.elitequotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elitequotes.favourite.FavouriteElementsContainer;
import com.elitequotes.favourite.FavouriteItem;

/**
 * Created by veinhorn on 24.3.14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "EliteQuotesDB";

    private final static String FAVOURITE_TABLE = "favourite";
    private final static String CREATE_FAVOURITE_TABLE = "CREATE TABLE " + FAVOURITE_TABLE + " ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "quote_text TEXT, " +
            "quote_author TEXT)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_FAVOURITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + FAVOURITE_TABLE);
        onCreate(database);
    }

    public void addFavourite(FavouriteItem favouriteItem) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quote_text", favouriteItem.getQuoteText());
        contentValues.put("quote_author", favouriteItem.getQuoteAuthor());
        database.insert(FAVOURITE_TABLE, null, contentValues);
        database.close();
    }

    public FavouriteElementsContainer getAllFavouriteElements() {
        FavouriteElementsContainer favouriteElementsContainer = new FavouriteElementsContainer();
        String selectAllQuery = "SELECT * FROM " + FAVOURITE_TABLE;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectAllQuery, null);
        if(cursor.moveToFirst()) {
            do {
                FavouriteItem favouriteItem = new FavouriteItem(cursor.getString(1), cursor.getString(2));
                favouriteElementsContainer.getFavouriteItemsArrayList().add(favouriteItem);

            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return favouriteElementsContainer;
    }
}
