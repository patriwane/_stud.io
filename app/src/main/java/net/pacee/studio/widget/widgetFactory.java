package net.pacee.studio.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import net.pacee.studio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mupac_000 on 28-07-17.
 */

public class WidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    List<String> list = new ArrayList();;
    Context ctx;

    public WidgetFactory(Context ctx)
    {
        this.ctx = ctx;
        populate();
    }
    public void populate()
    {
        for(int i = 1; i <=10;i++)
        {
            list.add("item:"+i);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(this.ctx.getPackageName(), android.R.layout.simple_list_item_1);
        Log.e("widetFactory","get Widet at "+i);
        rv.setTextViewText(android.R.id.text1,this.list.get(i));
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
