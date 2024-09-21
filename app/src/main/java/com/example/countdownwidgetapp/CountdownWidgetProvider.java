static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                            int appWidgetId) {

    // Get stored event name and date
    SharedPreferences prefs = context.getSharedPreferences("widget_prefs", Context.MODE_PRIVATE);
    String eventName = prefs.getString("event_name_" + appWidgetId, "Event");
    String dateString = prefs.getString("event_date_" + appWidgetId, "2021-12-31");

    // Parse date string
    String[] dateParts = dateString.split("-");
    int year = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]) - 1; // Months are 0-based
    int day = Integer.parseInt(dateParts[2]);

    // Calculate days left
    Calendar today = Calendar.getInstance();
    Calendar eventDate = Calendar.getInstance();
    eventDate.set(year, month, day);

    long diff = eventDate.getTimeInMillis() - today.getTimeInMillis();
    int daysLeft = (int) (diff / (1000 * 60 * 60 * 24));

    // Update the widget views
    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
    views.setTextViewText(R.id.days_left, daysLeft + " days left");
    views.setTextViewText(R.id.custom_text, eventName);

    // Update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views);
}
