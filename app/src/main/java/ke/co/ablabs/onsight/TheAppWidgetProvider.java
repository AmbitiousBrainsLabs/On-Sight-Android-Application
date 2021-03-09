package ke.co.ablabs.onsight;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class TheAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int widgetID : appWidgetIds){

            SharedPreferences onSightSharedPreferences = context.getSharedPreferences("ke.co.ablabs.onsight", Context.MODE_PRIVATE);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.the_widget_screen);
            remoteViews.setTextViewText(R.id.theWidgetTextView, onSightSharedPreferences.getString("the_note_data", "Note"));

            appWidgetManager.updateAppWidget(widgetID, remoteViews);
        }
    }
}
