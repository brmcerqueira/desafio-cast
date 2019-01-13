package com.desafio.cast.presentation.controllers;

import com.desafio.cast.business.PersonService;
import com.desafio.cast.domain.Person;
import com.desafio.cast.dto.PersonSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/rest")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @RequestMapping(value="/pessoas", method = RequestMethod.GET)
    public List<Person> all() {
        return this.service.all();
    }

    @RequestMapping(value="/pessoa/save", method = RequestMethod.POST)
    public void save(@Valid @RequestBody PersonSaveDto dto) {
        this.service.save(dto);
    }

    @RequestMapping(value="/pessoa/{id}", method = RequestMethod.GET)
    public Person find(@PathVariable long id) {
        return this.service.find(id);
    }

    @RequestMapping(value="/pessoa/remove/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        this.service.remove(id);
    }
}