package com.desafio.cast.persistence;

import com.desafio.cast.domain.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonDao {
    @Select("select * from person")
    List<Person> all();

    @Insert("insert into person(name, street, number, neighborhood, city, state, cellphone, phone) values(#{person.name}, #{person.street}, #{person.number}, #{person.neighborhood}, #{person.city}, #{person.state}, #{person.cellphone}, #{person.phone})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    void save(@Param("person") Person person);

    Person find(long id);

    void remove(long id);
}