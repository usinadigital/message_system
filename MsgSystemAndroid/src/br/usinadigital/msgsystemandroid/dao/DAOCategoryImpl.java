package br.usinadigital.msgsystemandroid.dao;

import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import br.usinadigital.msgsystemandroid.util.Constants;

public class DAOCategoryImpl implements DAOCategory{
	
	SharedPreferences prefName;
	SharedPreferences prefCheck;
	
	public DAOCategoryImpl(SharedPreferences prefName, SharedPreferences prefCheck) {
		super();
		this.prefName = prefName;
		this.prefCheck = prefCheck;
	}

	public void saveState(String id, boolean checked){
		Editor edCheck = prefCheck.edit();
		if ( checked ){
			edCheck.putString(id, Constants.CHECKED_STATE);
		}else{
			edCheck.remove(id);
		}
		edCheck.commit();
	}
	
	public void printData(){
		Map<String, ?> allEntries = prefCheck.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
		    Log.d(Constants.TAG, "prefCheck= " + entry.getKey() + ": " + entry.getValue().toString());
		} 
	}
}
