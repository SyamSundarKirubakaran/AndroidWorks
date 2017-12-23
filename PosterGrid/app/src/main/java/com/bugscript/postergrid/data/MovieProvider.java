package com.bugscript.postergrid.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.bugscript.postergrid.data.MovieContract.MovieEntry;

public class MovieProvider extends ContentProvider {
    private static final int MOVIE_DETAIL = 2;
    private static final int MOVIE_LIST = 1;
    static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(MovieContract.AUTHORITY, "movies", MOVIE_LIST);
        sUriMatcher.addURI(MovieContract.AUTHORITY, "#", MOVIE_DETAIL);
    }

    MovieDBHelper DBHelper;
    SQLiteDatabase database;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                count = database.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case MOVIE_DETAIL: {
                count = database.delete(MovieEntry.TABLE_NAME, MovieEntry.COLUMN_ID + " = ?", selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri returnUri;
        long _id = database.insert(MovieEntry.TABLE_NAME, null, values);
        if (_id > 0) {
            returnUri = ContentUris.withAppendedId(MovieContract.CONTENT_URI, _id);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        DBHelper = new MovieDBHelper(getContext());
        database = DBHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        if (sortOrder == null) sortOrder = MovieEntry.COLUMN_ID;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                retCursor = database.query(
                        MovieEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            }
            case MOVIE_DETAIL: {
                retCursor = database.query(
                        MovieEntry.TABLE_NAME, projection, MovieEntry.COLUMN_ID + " = ?",
                        new String[]{uri.getLastPathSegment()}, null, null, sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not needed");
    }
}
