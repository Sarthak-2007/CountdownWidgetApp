package com.example.countdownwidgetapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;

public class CountdownWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Calculate days left in the year
        Calendar today = Calendar.getInstance();
        Calendar endOfYear = Calendar.getInstance();
        endOfYear.set(Calendar.MONTH, Calendar.DECEMBER);
        endOfYear.set(Calendar.DAY_OF_MONTH, 31);

        long diff = endOfYear.getTimeInMillis() - today.getTimeInMillis();
        int daysLeft = (int) (diff / (1000 * 60 * 60 * 24));

        // Create the view for the widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.days_left, daysLeft + " days left");
        views.setTextViewText(R.id.custom_text, "Year End");

        // Update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
