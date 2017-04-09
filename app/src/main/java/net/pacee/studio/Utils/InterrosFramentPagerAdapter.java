package net.pacee.studio.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.pacee.studio.BlankFragment;
import net.pacee.studio.model.Interro;
import net.pacee.studio.model.Question;

import java.util.List;

public class InterrosFramentPagerAdapter extends FragmentPagerAdapter {
    public void setInterroList(List<Question> interroList) {
        this.questions = interroList;
    }

    List<Question> questions;
    private Context context;

    public InterrosFramentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        if(questions==null)
        return 0;

        return questions.size();
    }

    @Override
    public Fragment getItem(int position) {
        Question q = questions.get(position);
        return BlankFragment.newInstance(q.getQuestion(),q.getAnswer());
    }


}