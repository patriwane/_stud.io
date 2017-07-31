package net.pacee.studio.sync;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by mupac_000 on 29-07-17.
 */

public class MatiereSyncIntentService extends IntentService {
    MatiereSyncIntentService()
    {
        super("MatiereSyncIntentService");
    }
    @Override
    /**
     * mets les matières à jour
     */
    protected void onHandleIntent(Intent intent) {
        StudioSyncTask.syncMatiere(this);
    }
}
