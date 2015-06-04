package br.usinadigital.msgsystemandroid.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Message {

	private int id;
	
	private String title;
	
	private String text;
	
	private Date creationdate;
	
	private Date lastupdate;
	
	public Message() {

	}
	
	public Message(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	@Override
	public String toString(){
	        return this.toString();
	}
}
