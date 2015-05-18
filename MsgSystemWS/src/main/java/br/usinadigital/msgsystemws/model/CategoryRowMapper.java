package br.usinadigital.msgsystemws.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category>
{
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category Category = new Category();
		Category.setId(rs.getInt("id"));
		Category.setName(rs.getString("name"));
		Category.setDescription(rs.getString("description"));
		Category.setValid(rs.getInt("valid"));
		Category.setLastupdate(rs.getDate("lastupdate"));
		return Category;
	}
}
