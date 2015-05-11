package br.usinadigital.msgsystemws.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.usinadigital.msgsystemws.util.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Message {

	private int id;
	private String text;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date lastupdate;
	private Set<Category> categories = new HashSet<Category>(0);

	public Message() {

	}

	public Message(String text) {
		this.text = text;
	}
	
	public Message(String text, Set<Category> categories) {
		this.text = text;
		this.categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "id=" + id + ", text=" + text;
	}
}
