package br.usinadigital.msgsystemwebapp.model;

import java.util.ArrayList;
import java.util.List;

public class MessageForm {
	
	private String text;
	private List<Category> categories;
	
	public MessageForm(){
	}
	
	public MessageForm(String text, List<Category> categories) {
		super();
		this.text = text;
		this.categories = categories;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "MessageForm [text=" + text + ", categories=" + categories + "]";
	}
	
}
