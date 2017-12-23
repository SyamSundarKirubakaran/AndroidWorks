package com.bugscript.postergrid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.Uri;

import com.bugscript.postergrid.data.MovieContract;
import com.bugscript.postergrid.data.MovieContract.MovieEntry;

import java.util.ArrayList;

public class MoviesDB {
    public static String[] fav_movies;
    public static String[] fav_dates;
    public static String[] fav_summary;
    public static String[] fav_votes;
    public static String[] fav_poster;
    public static String[] fav_backdrop;
    public static String[] fav_id;
    static final String AUTHORITY_Uri = "content://" + MovieContract.AUTHORITY;

    public boolean isMovieFavorited(ContentResolver contentResolver, int id){
        boolean ret = false;
        Cursor cursor = contentResolver.query(Uri.parse(AUTHORITY_Uri + "/" + id), null, null, null, null, null);
        if (cursor != null && cursor.moveToNext()){
            ret = true;
            cursor.close();
        }
        return ret;
    }

    public void addMovie(ContentResolver contentResolver, int i){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieEntry.COLUMN_ID, MainActivity.id[i]);
        contentValues.put(MovieEntry.COLUMN_NAME, MainActivity.movies[i]);
        contentValues.put(MovieEntry.COLUMN_OVERVIEW, MainActivity.summary[i]);
        contentValues.put(MovieEntry.COLUMN_POSTER, MainActivity.poster[i]);
        contentValues.put(MovieEntry.COLUMN_BACKDROP, MainActivity.backdrop[i]);
        contentValues.put(MovieEntry.COLUMN_RATING, MainActivity.votes[i]);
        contentValues.put(MovieEntry.COLUMN_RELEASE, MainActivity.dates[i]);
        contentResolver.insert(Uri.parse(AUTHORITY_Uri + "/movies"), contentValues);
    }

    public void removeMovie(ContentResolver contentResolver, int id){
        Uri uri = Uri.parse(AUTHORITY_Uri + "/" + id);
        contentResolver.delete(uri, null, new String[]{id + ""});
    }

    public void getFavoriteMovies(ContentResolver contentResolver){
        Uri uri = Uri.parse(AUTHORITY_Uri + "/movies");
        Cursor cursor = contentResolver.query(uri, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){

            fav_id=new String[cursor.getCount()];
            fav_backdrop=new String[cursor.getCount()];
            fav_dates=new String[cursor.getCount()];
            fav_movies=new String[cursor.getCount()];
            fav_poster=new String[cursor.getCount()];
            fav_summary=new String[cursor.getCount()];
            fav_votes=new String[cursor.getCount()];

            for(int i=0;i<cursor.getCount();i++){
                fav_id[i] = cursor.getInt(cursor.getColumnIndex(MovieEntry.COLUMN_ID))+"";
                fav_movies[i] = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_NAME));
                fav_summary[i] = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_OVERVIEW));
                fav_votes[i] = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RATING));
                fav_poster[i] = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_POSTER));
                fav_backdrop[i]=cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_BACKDROP));
                fav_dates[i]= cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RELEASE));
                cursor.moveToNext();
            }
            cursor.close();
        }else{
            fav_id=null;
            fav_backdrop=null;
            fav_dates=null;
            fav_movies=null;
            fav_poster=null;
            fav_summary=null;
            fav_votes=null;
        }
    }


}