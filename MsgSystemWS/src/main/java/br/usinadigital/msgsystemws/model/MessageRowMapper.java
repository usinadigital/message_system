package br.usinadigital.msgsystemws.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MessageRowMapper implements RowMapper<Message>
{
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setText(rs.getString("text"));
		message.setCreationdate(rs.getDate("creationdate"));
		message.setCategories(null);
		message.setLastupdate(rs.getDate("lastupdate"));
		return message;
	}
}
