package br.usinadigital.msgsystemws.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.model.MessageRowMapper;
import br.usinadigital.msgsystemws.util.Constants;

public class MessageDAOImpl implements MessageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Transactional
	public void save(Message msg) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "INSERT INTO messages(text) VALUES (?)";
		logger.debug("Execute query: " + query);
		jdbcTemplate.update(query, new Object[] { msg.getText() });
		Integer idInsertedMessage = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
		logger.debug("ID value inserted=" + idInsertedMessage);
		categoriesBatchUpdate(idInsertedMessage, new ArrayList<Category>(msg.getCategories()));
	}

	public int[] categoriesBatchUpdate(final int idMessage, final List<Category> categories) {
		String sql = "INSERT INTO messages_categories(message_id,category_id) VALUES (?,?)";
		logger.debug("Execute batch insert: " + sql);
		int[] updateCnt = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, idMessage);
				ps.setInt(2, categories.get(i).getId());
			}

			@Override
			public int getBatchSize() {
				return categories.size();
			}
		});
		return updateCnt;
	}

	public void send(Message msg) {
		save(msg);
		// TODO get all users subscribed at the categories of the message to
		// send and send the messages to the users
	}

	public List<Message> getAll() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM " + Constants.TABLE_MESSAGE;
		List<Message> messageList = jdbcTemplate.query(sql, new MessageRowMapper());

		return messageList;
	}

	public List<Message> getMessagesFromDateByCategories(Date fromDate, List<Category> categories) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM " + Constants.TABLE_MESSAGE;
		sql += " INNER JOIN " + Constants.TABLE_CATEGORY;
		sql += " ON messages.id = categories.id WHERE creationdate >= ?";
		List<Message> messageList = jdbcTemplate.query(sql, new Object[] { fromDate }, new MessageRowMapper());

		return messageList;
	}
}
