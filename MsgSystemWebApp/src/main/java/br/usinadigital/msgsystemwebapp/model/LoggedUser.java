package br.usinadigital.msgsystemwebapp.model;

public class LoggedUser {
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoggedUser[username=");
		builder.append(username);
		builder.append("]");
		
		return builder.toString();
	}
	
	
}
