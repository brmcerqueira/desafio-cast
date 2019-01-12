package com.desafio.cast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiffControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getDiffControllerUri() {
        return "http://localhost:" + port + "/v1/diff/";
    }

    private Object diagnosisReturnObject() {
        return this.restTemplate.getForObject(getDiffControllerUri() + "diagnosis", Object.class);
    }

    private void clear() {
        this.restTemplate.headForHeaders(getDiffControllerUri() + "clear");
    }

    private void setData(String data, String path) {
        Base64.Encoder encoder = Base64.getEncoder();
        this.restTemplate.headForHeaders(getDiffControllerUri()
                + encoder.encodeToString(data.getBytes())
                + path);
    }

    private void setRight(String data) {
        setData(data, "/right");
    }

    private void setLeft(String data) {
        setData(data, "/left");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setLeftOnlyLeft() {
        clear();
        setLeft("test");
        LinkedHashMap<String, String> result = (LinkedHashMap<String, String>)diagnosisReturnObject();
        assertTrue(result.containsKey("message"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setLeftOnlyRight() {
        clear();
        setRight("test");
        LinkedHashMap<String, String> result = (LinkedHashMap<String, String>) diagnosisReturnObject();
        assertTrue(result.containsKey("message"));
    }

    @Test
    public void whenIsEqual() {
        setLeft("test");
        setRight("test");
        Object result = diagnosisReturnObject();
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean)result);
    }

    @Test
    public void whenSizeIsDifferent() {
        setLeft("test_");
        setRight("test");
        Object result = diagnosisReturnObject();
        assertTrue(result instanceof Integer);
        assertEquals(1, ((Integer)result).intValue());
    }

    @Test
    public void whenIsDifferent() {
        setLeft("test_test_test_sat");
        setRight("test_sats_test_set");
        ArrayList<DiffDto> result = this.restTemplate.exchange(getDiffControllerUri() + "diagnosis",
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<DiffDto>>() {}).getBody();
        assertEquals(2, result.size());
        DiffDto diff = result.get(0);
        assertEquals(5, diff.getOffset());
        assertEquals(4, diff.getLength());
        diff = result.get(1);
        assertEquals(16, diff.getOffset());
        assertEquals(1, diff.getLength());
    }
}