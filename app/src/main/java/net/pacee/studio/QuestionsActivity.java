package net.pacee.studio;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import net.pacee.studio.Utils.AsyncTaskLoader;
import net.pacee.studio.Utils.InterroAdapter;
import net.pacee.studio.Utils.InterrosFramentPagerAdapter;
import net.pacee.studio.model.Interro;
import net.pacee.studio.model.Matiere;
import net.pacee.studio.model.Question;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static net.pacee.studio.Utils.ConnectionUtils.getInterro;
import static net.pacee.studio.Utils.ConnectionUtils.getInterros;

public class QuestionsActivity extends AppCompatActivity {

    List<Question> questions;
    ViewPager viewPager;
    InterrosFramentPagerAdapter ifpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        viewPager = (ViewPager) findViewById(R.id.vp_questions);
        ifpa = new InterrosFramentPagerAdapter(getSupportFragmentManager(),this);



        AsyncTaskLoader asyncTaskLoader = new AsyncTaskLoader();
        asyncTaskLoader.setCallback(new AsyncTaskLoader.AsyncCallback() {
            @Override
            public void getJson(String jsonData) {
                show(jsonData);
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {


            Interro interro = (Interro) bundle.getSerializable("interro");
            Log.i("interro",interro.getId());
            URL url = getInterro(Integer.parseInt(interro.getId()));
            asyncTaskLoader.execute(url);

        }
    }

    private void show(String jsonData) {
        System.out.println("yolo:"+jsonData);

        Gson gson = new Gson();
        questions = Arrays.asList(gson.fromJson(jsonData,Question[].class));
        ifpa.setInterroList(questions);
        viewPager.setAdapter(ifpa);
    }


}
