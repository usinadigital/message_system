package br.usinadigital.msgsystemandroid.model;

public class Message {

	private String id;
	
	private String title;
	
	private String text;
	
	private String creationdate;
	
	public Message() {
	}
	
	public Message(String id, String title, String text, String creationdate) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.creationdate = creationdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Message(id=").append(id)
		.append(", title=").append(title)
		.append(", text=").append(text)
		.append(", creationdate=").append(creationdate).append(")");
	        
		return sb.toString();
	}
}
