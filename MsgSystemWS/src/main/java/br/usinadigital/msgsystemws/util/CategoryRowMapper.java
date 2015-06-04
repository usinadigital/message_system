package br.usinadigital.msgsystemws.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.usinadigital.msgsystemws.model.Category;

public class CategoryRowMapper implements RowMapper<Category>
{
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category Category = new Category();
		Category.setId(rs.getInt("id"));
		Category.setName(rs.getString("name"));
		Category.setDescription(rs.getString("description"));
		Category.setValid(rs.getInt("valid"));
		Category.setLastupdate(rs.getTimestamp("lastupdate"));
		return Category;
	}
}
