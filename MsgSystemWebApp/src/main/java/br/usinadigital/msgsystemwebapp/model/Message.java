package br.usinadigital.msgsystemwebapp.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Message {

	private String text;
	
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString(){
	        return ToStringBuilder.reflectionToString(this);
	}
}
