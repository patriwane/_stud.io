package net.pacee.studio.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class MatieresProvider extends ContentProvider {

    public final static String CONTENT_AUTORITY = "net.pacee.studio.data";
    public static final int CODE_INTERRO = 100;
    public static final int CODE_INTERRO_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private StudioDhHelper mDHelper;
    public static UriMatcher buildUriMatcher() {

        /*
         * All paths added to the UriMatcher have a corresponding code to return when a match is
         * found. The code passed into the constructor of UriMatcher here represents the code to
         * return for the root URI. It's common to use NO_MATCH as the code for this case.
         */
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTORITY;

        /*
         * For each type of URI you want to add, create a corresponding code. Preferably, these are
         * constant fields in your class so that you can use them throughout the class and you no
         * they aren't going to change. In Sunshine, we use CODE_WEATHER or CODE_WEATHER_WITH_DATE.
         */

        /* This URI is content://com.example.android.sunshine/weather/ */
        matcher.addURI(authority, MatieresContract.PATH_INTERRO, CODE_INTERRO);

        /*
         * This URI would look something like content://com.example.android.sunshine/weather/1472214172
         * The "/#" signifies to the UriMatcher that if PATH_WEATHER is followed by ANY number,
         * that it should return the CODE_WEATHER_WITH_DATE code
         */
        matcher.addURI(authority, MatieresContract.PATH_INTERRO + "/#", CODE_INTERRO_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDHelper = new StudioDhHelper(getContext());
        return true;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mDHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case CODE_INTERRO:
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : values) {


                        long _id = db.insert(MatieresContract.MatiereEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }

                return rowsInserted;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;


        switch (sUriMatcher.match(uri)) {

            case CODE_INTERRO:
                cursor = mDHelper.getReadableDatabase().query(
                        MatieresContract.MatiereEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;

                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
            }


        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
