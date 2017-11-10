package net.pacee.studio.sync;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import net.pacee.studio.data.MatieresContract;
import net.pacee.studio.model.Interro;

import java.util.concurrent.TimeUnit;

/**
 * Created by mupac_000 on 31-07-17.
 */

public class SyncUtils {

    private static final int SYNC_INTERVAL_HOURS = 3;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS/3;

    private static String TAG = "matière-sync";

    private static boolean sInitialized;

    public static void scheduleJobDispatcherSynch(@NonNull final Context context)
    {
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job matiereJobSync = dispatcher.newJobBuilder()
                .setService(StudioJobService.class)
                .setTag(TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true) // pour garder les données à jour
                .setTrigger(Trigger.executionWindow(SYNC_INTERVAL_SECONDS,SYNC_INTERVAL_SECONDS+SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true) // si une nouveau job avec le meme tag est enregisté, celui-ci est remplacé
                .build();
        dispatcher.schedule(matiereJobSync);
    }

    synchronized public static void initialize(final Context ctx)
    {
        /*
        * lancer 1 seule fois par application
         */
        if(sInitialized) return;

        sInitialized = true;
        scheduleJobDispatcherSynch(ctx);

        /*
        * il faut vérifier si de données ont déjà été enregistré
        * mais le fait sur le thread principale va geler l'affichage
         */
        Thread checkForEmpty = new Thread(new Runnable()
        {

            @Override
            public void run() {
                Uri matiereUri = MatieresContract.MatiereEntry.CONTENT_URI;
                Cursor c = ctx.getContentResolver().query(matiereUri,null,null,null,null);

                if(null == c || c.getCount() == 0)
                {
                    startImadiateSync(ctx);
                }
                c.close(); // prevent memory leaks

            }
        });
        checkForEmpty.start();

    }

    private static void startImadiateSync(Context ctx) {
        Intent i = new Intent(ctx, MatiereSyncIntentService.class);
        ctx.startService(i);
    }
}
