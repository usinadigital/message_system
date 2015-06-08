package br.usinadigital.msgsystemandroid.dao;

import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.usinadigital.msgsystemandroid.model.Message;

public class MessageDAOImpl implements MessageDAO{

	SharedPreferences msgTitle;
	SharedPreferences msgTxt;
	SharedPreferences msgDate;

	public MessageDAOImpl(SharedPreferences msgTitle, SharedPreferences msgTxt, SharedPreferences mesDate) {
		this.msgTitle = msgTitle;
		this.msgTxt = msgTxt;
		this.msgDate = mesDate;
	}

	public Message[] getAll(){
		Map mapTitle = msgTitle.getAll();
		Map mapTxt = msgTxt.getAll();
		Map mapDate = msgDate.getAll();
		Message[] msgs = new Message[mapDate.size()];
		int i=0;
		for( Object key : mapDate.keySet()){
			String id = (String)key;
			String title = (String)mapTitle.get(key);
			String text = (String)mapTxt.get(key);
			String date = (String)mapDate.get(key);
			msgs[i] = new Message(id,title,text,date);
		}
		return msgs;
	}
	
	public void save(Message[] msgs){
		Editor edTitle =  msgTitle.edit();
		Editor edTxt =  msgTxt.edit();
		Editor edDate =  msgDate.edit();
		for (int i=0; i < msgs.length; i++) {
			String id = String.valueOf(msgs[i].getId());
			edTitle.putString(id, msgs[i].getTitle()).commit();
			edTxt.putString(id, msgs[i].getText()).commit();
			edDate.putString(id, msgs[i].getCreationdate()).commit();
		}
	}
}
