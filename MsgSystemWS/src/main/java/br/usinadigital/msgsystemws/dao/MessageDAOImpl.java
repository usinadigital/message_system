package br.usinadigital.msgsystemws.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.Message;
import br.usinadigital.msgsystemws.model.MessageRowMapper;
import br.usinadigital.msgsystemws.util.Constants;

public class MessageDAOImpl implements MessageDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
     
    public void save(Message msg) {
        
    }
     
    public List<Message> list() {
    	jdbcTemplate = new JdbcTemplate(dataSource);
    	String sql = "SELECT * FROM " + Constants.TABLE_MESSAGE;
    	List<Message> messageList = jdbcTemplate.query(sql,new MessageRowMapper());
        
    	return messageList;
    }

    public List<Message> getMessagesFromDateByCategories(Date fromDate, List<Category> categories) {
    	jdbcTemplate = new JdbcTemplate(dataSource);
    	String sql = "SELECT * FROM " + Constants.TABLE_MESSAGE;
    	sql += " INNER JOIN " + Constants.TABLE_CATEGORY;
    	sql += " ON messages.id = categories.id WHERE creationdate >= ?";
    	List<Message> messageList = jdbcTemplate.query(sql,new Object[] { fromDate },new MessageRowMapper());
        
    	return messageList;
    }
}
