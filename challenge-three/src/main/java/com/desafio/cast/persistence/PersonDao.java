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
    void save(@Param("person") Person person);

    @Select("select * from person where id = #{id}")
    Person find(@Param("id") long id);

    @Delete("delete from person where id = #{id}")
    void remove(@Param("id") long id);
}