package com.desafio.cast.business;

import com.desafio.cast.domain.Person;
import com.desafio.cast.dto.PersonSaveDto;
import com.desafio.cast.persistence.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonDao dao;

    @Autowired
    public PersonService(PersonDao dao) {
        this.dao = dao;
    }

    public void save(PersonSaveDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        this.dao.save(person);
    }

    public List<Person> all() {
        return this.dao.all();
    }

    public Person find(long id) {
        return this.dao.find(id);
    }

    public void remove(long id) {
        this.dao.remove(id);
    }
}