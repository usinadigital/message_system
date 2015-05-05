package br.usinadigital.msgsystem.dao;

import java.util.List;

import br.usinadigital.msgsystem.model.Person;
 
public interface PersonDAO {
 
    public void save(Person p);
     
    public List<Person> list();
     
}
