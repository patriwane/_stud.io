package net.pacee.studio.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import net.pacee.studio.data.MatieresContract;
import net.pacee.studio.model.Matiere;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static net.pacee.studio.Utils.ConnectionUtils.getMatieres;
import static net.pacee.studio.Utils.ConnectionUtils.getResponseFromHttpUrl;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class StudioSyncTask {
    /**
     * Cette méthode permet de telecharger des données au format json
     * de les transformer au format contentValues
     * @param ctx
     */
    synchronized public static void syncMatiere(Context ctx)
    {
        try
        {
            URL matiereRequestURL = getMatieres();
            String results = getResponseFromHttpUrl(matiereRequestURL);
            Gson gson = new Gson();
            Matiere[] mat = gson.fromJson(results,Matiere[].class);

            ContentValues[] matieres =  matieresToContentValues(mat);

            if(matieres != null && matieres.length != 0)
            {
                ContentResolver matieresResolver = ctx.getContentResolver();
                matieresResolver.bulkInsert(MatieresContract.MatiereEntry.CONTENT_URI,matieres);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ContentValues[] matieresToContentValues(Matiere[] matieres)
    {
        ContentValues[] cvs = new ContentValues[matieres.length];
        for(int i = 0; i< matieres.length;i++)
        {
            ContentValues cv = new ContentValues();
            Matiere m = matieres[i];
            cv.put(MatieresContract.MatiereEntry.COLUMN_IDINTERRO,m.getId());
            cv.put(MatieresContract.MatiereEntry.COLUMN_NAME,m.getNom());
            cvs[i] = cv;
        }
        return cvs;
    }
}
