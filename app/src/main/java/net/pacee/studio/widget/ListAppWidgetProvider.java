package net.pacee.studio.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import net.pacee.studio.R;

import static net.pacee.studio.R.layout.example_appwidget;

public class ListAppWidgetProvider extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; ++i) {
            int appWidgetId = appWidgetIds[i];

            //wich layout to show
            RemoteViews views = new RemoteViews(context.getPackageName(), example_appwidget);
            //remote service to provide adapter for listview
            Intent intent = new Intent(context,WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter(R.id.listview,intent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }
}
