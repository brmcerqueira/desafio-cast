package com.desafio.cast.business;

import com.desafio.cast.domain.Person;
import com.desafio.cast.dto.PersonOutputDto;
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
        person.setStreet(dto.getStreet());
        person.setNumber(dto.getNumber());
        person.setNeighborhood(dto.getNeighborhood());
        person.setCity(dto.getCity());
        person.setState(dto.getState());
        person.setCellphone(dto.getCellphone());
        person.setPhone(dto.getPhone());

        if (dto.getId() != null) {
            person.setId(dto.getId());
            this.dao.update(person);
        }
        else {
            this.dao.create(person);
        }
    }

    public List<PersonOutputDto> all() {
        return this.dao.all();
    }

    public Person find(long id) {
        return this.dao.find(id);
    }

    public void remove(long id) {
        this.dao.remove(id);
    }
}
