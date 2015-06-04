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
import br.usinadigital.msgsystemws.util.MessageRowMapper;
import br.usinadigital.msgsystemws.util.Utils;

public class MessageDAOImpl implements MessageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Transactional
	public int save(Message msg) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "INSERT INTO messages(title,text) VALUES (?,?)";
		logger.debug("Execute query: " + query);
		int n = jdbcTemplate.update(query, new Object[] { msg.getTitle(), msg.getText() });
		Integer idInsertedMessage = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
		logger.debug("ID value inserted=" + idInsertedMessage);
		categoriesBatchUpdate(idInsertedMessage, new ArrayList<Category>(msg.getCategories()));
		return n;
	}

	private int categoriesBatchUpdate(final int idMessage, final List<Category> categories) {
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
		
		return Utils.sumIntArray(updateCnt);
	}

	public List<Message> getAll() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM messages";
		List<Message> messageList = jdbcTemplate.query(sql, new MessageRowMapper());

		return messageList;
	}

	public List<Message> getMessagesFromDateByCategories(Date fromDate, int[] categoriesId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql0 = String.format("select id FROM categories WHERE categories.id IN (%s)",Utils.intArrayToString(categoriesId));
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT message_id, title, text, creationdate, lastupdate ");
		sql.append("FROM messages_categories ");
		sql.append("INNER JOIN messages ON message_id = messages.id ");
		sql.append("INNER JOIN (" ).append(sql0).append( ") AS categories ON category_id = categories.id ");
		sql.append("WHERE messages.creationdate >= ? ");
		sql.append("GROUP BY message_id ORDER BY creationdate ");
		
		List<Message> messageList = jdbcTemplate.query(sql.toString(), new Object[] { fromDate }, new MessageRowMapper());

		return messageList;
	}

}
