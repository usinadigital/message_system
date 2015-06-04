package br.usinadigital.msgsystemws.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.usinadigital.msgsystemws.model.Message;

public class MessageRowMapper implements RowMapper<Message>
{
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setTitle(rs.getString("title"));
		message.setText(rs.getString("text"));
		message.setCreationdate(rs.getDate("creationdate"));
		message.setLastupdate(rs.getDate("lastupdate"));
		message.setCategories(null);
		return message;
	}
}
