package br.usinadigital.msgsystemandroid.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.usinadigital.msgsystemandroid.model.Message;
import br.usinadigital.msgsystemandroid.util.Utils;

public class MessageDAOImpl implements MessageDAO{

	SharedPreferences messages;
	
	public MessageDAOImpl(SharedPreferences messages) {
		this.messages = messages;
	}

	public Map<String,Set<String>> getAll(){
		Map<String,Set<String>> res = new HashMap<String,Set<String>>();
		res = (Map<String,Set<String>>)messages.getAll();
		for( String id : res.keySet()){
			
		}
		return res;
	}
	
	public void save(Message[] msgs){
		Editor editor =  messages.edit();
		for (int i=0; i < msgs.length; i++) {
			String id = String.valueOf(msgs[i].getId());
			String[] array = new String[] {msgs[i].getTitle(), msgs[i].getText(), msgs[i].getCreationdate()};
			editor.putStringSet( id, new HashSet<String>(Arrays.asList(array)) );
		}
		editor.commit();
	}
}
