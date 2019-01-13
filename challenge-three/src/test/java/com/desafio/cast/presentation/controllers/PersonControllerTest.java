package com.desafio.cast.presentation.controllers;

import com.desafio.cast.domain.Person;
import com.desafio.cast.dto.PersonSaveDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getHostUri() {
        return "http://localhost:" + port + "/rest/";
    }

    @Test
    public void all() {
        ResponseEntity<ArrayList<Person>> response = this.restTemplate.exchange(getHostUri() + "pessoas", HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayList<Person>>() {});
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void save() {
        PersonSaveDto dto = new PersonSaveDto();
        dto.setName("Bruno Cerqueira");
        dto.setStreet("rua edson regis");
        dto.setNumber(685);
        dto.setNeighborhood("Janga");
        dto.setCity("Paulista");
        dto.setState("PE");
        dto.setCellphone("988452851");
        dto.setPhone("30301901");
        ResponseEntity<Void> response = this.restTemplate.exchange(getHostUri() + "pessoa/save", HttpMethod.PUT, new HttpEntity<>(dto), Void.class);
        assertEquals(200, response.getStatusCode().value());
    }
}