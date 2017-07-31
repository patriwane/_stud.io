package net.pacee.studio.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

/**
 * Created by mupac_000 on 28-07-17.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.e("widgetService","onGetViewFactory");
        return new WidgetFactory(this.getApplicationContext());
    }
}
