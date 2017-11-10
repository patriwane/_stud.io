package net.pacee.studio.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


/**
 * Created by mupac_000 on 31-07-17.
 */

public class StudioJobService extends JobService {

    private AsyncTask<Void,Void,Void> fetchMatieres;

    /**
     * Pour executer les tache en bg le plus vite possible
     * @param job
     * @return s'il y a encore du travail a realiser
     */
    @Override
    public boolean onStartJob(final JobParameters job) {
        fetchMatieres = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Context ctx = getApplicationContext();
                StudioSyncTask.syncMatiere(ctx);
                jobFinished(job,false);
                return null;
            }

            @Override
            protected void onPreExecute() {
                jobFinished(job,false);
            }
        };
        fetchMatieres.execute();
        return true;
    }

    /**
     * si le système suspend l'execution de la tache à cause d'un problème de contraintes système
     * @param job
     * @return s'il faut relancer le job
     */
    @Override
    public boolean onStopJob(JobParameters job) {
        if(fetchMatieres != null)
        {
            fetchMatieres.cancel(true);
        }
        return true;
    }
}
