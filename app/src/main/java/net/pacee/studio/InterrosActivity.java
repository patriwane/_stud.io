package net.pacee.studio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import net.pacee.studio.Utils.AsyncTaskLoader;
import net.pacee.studio.Utils.CustomAdapter;
import net.pacee.studio.Utils.InterroAdapter;
import net.pacee.studio.model.Interro;
import net.pacee.studio.model.Matiere;
import net.pacee.studio.model.Question;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static net.pacee.studio.Utils.ConnectionUtils.getInterro;
import static net.pacee.studio.Utils.ConnectionUtils.getInterros;
import static net.pacee.studio.Utils.ConnectionUtils.getMatieres;

public class InterrosActivity extends AppCompatActivity implements CustomAdapter.OnMatiereClickListener {

    RecyclerView recyclerView;
    InterroAdapter adapter;
    List<Interro> interroList;
    Matiere matiere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interros);
        recyclerView = (RecyclerView) findViewById(R.id.rv_interros_main);
        adapter = new InterroAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AsyncTaskLoader asyncTaskLoader = new AsyncTaskLoader();
        adapter.setListener(this);

        asyncTaskLoader.setCallback(new AsyncTaskLoader.AsyncCallback() {
            @Override
            public void getJson(String jsonData) {
                show(jsonData);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            matiere= (Matiere) bundle.getSerializable("interroId");
            Log.i("InterrosActivity",matiere.getId());
            URL url = getInterros(Integer.parseInt(matiere.getId()));
            asyncTaskLoader.execute(url);

        }
    }

    public void show(String txt)
    {
        System.out.println(txt);
        Gson gson = new Gson();
        interroList = Arrays.asList(gson.fromJson(txt,Interro[].class));
        adapter.setInterros(interroList);
    }


    @Override
    public void getPosition(int position) {
        Log.i("InterrosActivity",getInterro(Integer.parseInt(position+""))+"");
        Intent intent = new Intent(this,QuestionsActivity.class);
        intent.putExtra("interro",interroList.get(position));
        startActivity(intent);
    }
}
