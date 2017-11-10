package net.pacee.studio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class StudioDhHelper extends SQLiteOpenHelper {
    public static final String DATA_NAME = "studio.db";

    private static final int DATA_VERSION = 4;

    public StudioDhHelper(Context context) {
        super(context, DATA_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MatieresContract.MatiereEntry.CREATE_STMT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(MatieresContract.MatiereEntry.UPDATE_STMT);
        onCreate(db);
    }
}
