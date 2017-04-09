package net.pacee.studio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import net.pacee.studio.Utils.AsyncTaskLoader;
import net.pacee.studio.Utils.CustomAdapter;
import net.pacee.studio.model.Matiere;

import java.util.Arrays;
import java.util.List;

import static net.pacee.studio.Utils.ConnectionUtils.getMatieres;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnMatiereClickListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Matiere> matiereList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main_main);
        adapter = new CustomAdapter(this);
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

        asyncTaskLoader.execute(getMatieres());


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
}
