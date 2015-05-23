package br.usinadigital.msgsystemwebapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.usinadigital.msgsystemwebapp.util.CustomDateDeserializer;
import br.usinadigital.msgsystemwebapp.util.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Category {
	
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date lastupdate;
	
	// Is possible to have the category in the database for the history/statistic but not anymore used
	private int valid;
	
	private Set<Message> messages = new HashSet<Message>(0);
	
	public Category(){
		
	}
	
	public Category(int id) {
		this.id = id;
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(int id,String name) {
		this.id = id;
		this.name = name;
	}
	
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Category(String name, String description, int valid) {
		this.name = name;
		this.description = description;
		this.valid = valid;
	}
	
	public Category(String name, String description, int valid, Set<Message> messages) {
		this.name = name;
		this.description = description;
		this.valid = valid;
		this.messages = messages;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String country) {
		this.description = country;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}
	
	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> categories) {
		this.messages = categories;
	}
	
	@Override
	public String toString() {
		return "id=" + id + 
				", name=" + name + 
				", description=" + description +
				", valid=" + valid +
				", lastupdate=" + lastupdate;
	}
}
