package net.pacee.studio.sync;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class MatiereSyncIntentService extends IntentService {
    public MatiereSyncIntentService()
    {

        super("MatiereSyncIntentService");


    }
    @Override
    /**
     * mets les matières à jour
     */
    protected void onHandleIntent(Intent intent) {
        Log.e("MatiereSync","Sync");
        Toast.makeText(getApplicationContext(),"sync",Toast.LENGTH_SHORT);
        StudioSyncTask.syncMatiere(this);
    }
}
