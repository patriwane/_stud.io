package net.pacee.studio.data;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class MatieresContract {


    public static final String CONTENT_AUTHORITY = "net.pacee.studio";

    /*
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider for Sunshine.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INTERRO = "matieres";




    public static final class MatiereEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INTERRO)
                .build();
// todo : single couple of values
        public static final String TABLE_NAME = "matieres";
        public static final String COLUMN_NAME = "nom";
        public static final String COLUMN_IDINTERRO = "idMatiere";

        public static final String CREATE_STMT = "CREATE TABLE "+ TABLE_NAME +"("
                       //     + MatiereEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_IDINTERRO + " INTEGER NOT NULL,"
                            + COLUMN_NAME + " TEXT NOT NULL,"
                            +  " PRIMARY KEY ("+COLUMN_IDINTERRO+","+COLUMN_NAME+")"
                            +  " ON CONFLICT IGNORE"+
                            ");";


        public static final String UPDATE_STMT = "DROP TABLE IF EXISTS "+ TABLE_NAME ;


    }

}
