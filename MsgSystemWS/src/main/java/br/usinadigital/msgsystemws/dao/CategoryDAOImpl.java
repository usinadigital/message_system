package br.usinadigital.msgsystemws.dao;
 
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.usinadigital.msgsystemws.model.Category;
import br.usinadigital.msgsystemws.model.CategoryRowMapper;
import br.usinadigital.msgsystemws.util.Constants;

public class CategoryDAOImpl implements CategoryDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Category p) {
       
    }
 
    
    public List<Category> list() {
        
    	jdbcTemplate = new JdbcTemplate(dataSource);
    	String sql = "SELECT * FROM " + Constants.TABLE_CATEGORY;
    	List<Category> caregoryList = jdbcTemplate.query(sql,new CategoryRowMapper());
        
        return caregoryList;
    }
}
