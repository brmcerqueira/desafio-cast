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

    private Object diagnosisReturnObject(String left, String right) {
        setLeftAndRight(left, right);
        return this.restTemplate.getForObject(getDiffControllerUri() + "diagnosis", Object.class);
    }

    private void setLeftAndRight(String left, String right) {
        Base64.Encoder encoder = Base64.getEncoder();
        this.restTemplate.headForHeaders(getDiffControllerUri()
                + encoder.encodeToString(left.getBytes())
                + "/left");
        this.restTemplate.headForHeaders(getDiffControllerUri()
                + encoder.encodeToString(right.getBytes())
                + "/right");
    }

    @Test
    public void whenIsEqual() {
        Object result = diagnosisReturnObject("test", "test");
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean)result);
    }

    @Test
    public void whenSizeIsDifferent() {
        Object result = diagnosisReturnObject("test_", "test");
        assertTrue(result instanceof Integer);
        assertEquals(1, ((Integer)result).intValue());
    }

    @Test
    public void whenIsDifferent() {
        setLeftAndRight("test_test_test_sat", "test_sats_test_set");
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