package br.usinadigital.msgsystemws.dao;

import java.util.List;

import br.usinadigital.msgsystemws.model.Category;

public interface CategoryDAO {
	
	public void save(Category c);
    
    public List<Category> getAll();
}
