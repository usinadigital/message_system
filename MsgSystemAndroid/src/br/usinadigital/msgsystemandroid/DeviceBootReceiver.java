package br.usinadigital.msgsystemandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAO;
import br.usinadigital.msgsystemandroid.dao.ConfigurationDAOImpl;
import br.usinadigital.msgsystemandroid.util.Constants;
import br.usinadigital.msgsystemandroid.util.Utils;

/**
 * Used to reschedule the MessageService after rebooting of android
 *
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	
    	ConfigurationDAO configDAO = new ConfigurationDAOImpl(context.getSharedPreferences(Constants.FILE_CONFIGURATIONS, Context.MODE_PRIVATE));
    	int[] frequencyValues = context.getResources().getIntArray(R.array.array_frequency_values);
    	int freq = frequencyValues[configDAO.getUpdateFrequency()];
    	Utils.initializeMessageService(context,freq);
    	Log.d(Constants.TAG, "Message Service Scheduled after rebooting with freq " + freq);
    }
}
