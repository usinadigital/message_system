package br.usinadigital.msgsystemandroid.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.usinadigital.msgsystemandroid.R;

public class NotificationHelper{
	
	@SuppressWarnings("deprecation")
	public static void notify(Context context, int id, String notificationTitle, String notificationMessage) {	
//		Intent notificationIntent = new Intent(this, NotificationActivity.class);
//		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
		
		//Intent notificationIntent = new Intent(context, NotificationActivity.class);
		//notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, context.getString(R.string.newMessageNotification), System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		notification.setLatestEventInfo(context, notificationTitle, notificationMessage, resultPendingIntent);
		notificationManager.notify(id, notification);
	}
}