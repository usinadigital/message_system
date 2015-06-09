package br.usinadigital.msgsystemandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.Utils;

/**
 * Used to reschedule the MessageService after rebooting of android
 *
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	Utils.initializeMessageService(context,Constants.FREQUENCY_TEST);
    	Log.d(Constants.TAG, "Message Service Scheduled after rebooting");
    }
}
