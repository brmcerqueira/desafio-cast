package com.desafio.cast.persistence;

import com.desafio.cast.domain.Person;
import com.desafio.cast.dto.PersonOutputDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonDao {
    @Select("select id, name from person")
    List<PersonOutputDto> all();

    @Insert("insert into person(name, street, number, neighborhood, city, state, cellphone, phone) values(#{person.name}, #{person.street}, #{person.number}, #{person.neighborhood}, #{person.city}, #{person.state}, #{person.cellphone}, #{person.phone})")
    void create(@Param("person") Person person);

    @Update("update person set name = #{person.name}, street = #{person.street}, number = #{person.number}, neighborhood = #{person.neighborhood}, city = #{person.city}, state = #{person.state}, cellphone = #{person.cellphone}, phone = #{person.phone} where id = #{person.id}")
    void update(@Param("person") Person person);

    @Select("select * from person where id = #{id}")
    Person find(@Param("id") long id);

    @Delete("delete from person where id = #{id}")
    void remove(@Param("id") long id);
}