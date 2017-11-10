package net.pacee.studio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;


import net.pacee.studio.Utils.CustomAdapter;
import net.pacee.studio.data.MatieresContract;
import net.pacee.studio.model.Matiere;
import net.pacee.studio.sync.MatiereSyncIntentService;
import net.pacee.studio.sync.SyncUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnMatiereClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private boolean init = false;
    private static final int LOADER_ID = 55;
    private Cursor data;
    private static final String[] MATIERE_QUERY_PROJECTION = {
            MatieresContract.MatiereEntry.COLUMN_NAME,
            MatieresContract.MatiereEntry.COLUMN_IDINTERRO
    };
    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Matiere> matiereList;
    LoaderManager loader;
    TextView loaderScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderScreen = (TextView) findViewById(R.id.loading_screen);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main_main);
        adapter = new CustomAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        AsyncTaskLoader asyncTaskLoader = new AsyncTaskLoader();
        adapter.setListener(this);
//        asyncTaskLoader.setCallback(new AsyncTaskLoader.AsyncCallback() {
//            @Override
//            public void getJson(String jsonData) {
//                show(jsonData);
//            }
//        });
//        asyncTaskLoader.execute(getMatieres());
        //saving data to db
        //startImmediateSync(this);
        //Loading data from db

        // TODO : test if it worked to 1) instant load for first time
        // TODO : test if it worked to 2) if dispatch effectively load at the time scheduled

        loader = getSupportLoaderManager();
        loader.initLoader(LOADER_ID, null, this);
        SyncUtils.initialize(this);
    }

    @Override
    protected void onResume() {

    super.onResume();
        if(!loader.hasRunningLoaders())
            loader.restartLoader(LOADER_ID, null, this);


    }

    public void show(String txt)
    {
        Gson gson = new Gson();
        matiereList = Arrays.asList(gson.fromJson(txt,Matiere[].class));
        System.out.println("show:"+txt);
        adapter.setMatieres(matiereList);
    }


    @Override
    public void getPosition(int position) {
        Intent intent = new Intent(this,InterrosActivity.class);
        intent.putExtra("interroId", matiereList.get(position));
        startActivity(intent);
    }

    /**
     * Enregister le données venu d'internet (json)
     * dans la base de données locale
     * @param context le contexte
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Log.e("MainActicity","startImmediateSync");
        Intent intentToSyncImmediately = new Intent(context, MatiereSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("MainActivity","Loading");
        if(loaderScreen==null)
            loaderScreen = (TextView) findViewById(R.id.loading_screen);
        switch(id)
        {
            case LOADER_ID:
            {
                Uri queryUri = MatieresContract.MatiereEntry.CONTENT_URI;
                return new CursorLoader(this,queryUri,MATIERE_QUERY_PROJECTION,null,null,null);
            }
            default:
                throw new RuntimeException("Loader not implemented yet : "+ id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

         deCursorify(data);
            loaderScreen.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.setMatieres(null);
    }

    public void deCursorify(Cursor data)
    {
        Log.e("MainA-decursorify","deCursorify");
        if(data!=null && data.getCount()!=0)
        {
            matiereList = new ArrayList<>();
            Log.e("MainA-decursorify","read data");
            while (data.moveToNext())
            {
                String name = data.getString(data.getColumnIndex(MatieresContract.MatiereEntry.COLUMN_NAME));
                String id = String.valueOf(data.getInt(data.getColumnIndex(MatieresContract.MatiereEntry.COLUMN_IDINTERRO)));
                Matiere matiere = new Matiere(id,name);
                Toast.makeText(this,matiere.toString(),Toast.LENGTH_SHORT);
                Log.i("MainA-decursorify", matiere.toString());
                matiereList.add(matiere);
            }
            data.close();
            adapter.setMatieres(matiereList);
            adapter.notifyDataSetChanged();
        }else
        {
            Log.e("MainA-decursorify","failed to read data"+data.getCount());
        }
    }
    //region menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menusettings:
                Intent i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }

    //endregion
}
