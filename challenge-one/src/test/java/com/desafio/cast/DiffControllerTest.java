package com.desafio.cast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

import static junit.framework.TestCase.assertTrue;

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

    @Test
    public void success() {
        Base64.Encoder encoder = Base64.getEncoder();
        this.restTemplate.headForHeaders(getDiffControllerUri()
                + encoder.encodeToString("test".getBytes())
                + "/left");
        this.restTemplate.headForHeaders(getDiffControllerUri()
                + encoder.encodeToString("test".getBytes())
                + "/right");
        DiagnosisDto diagnosisDto = this.restTemplate.getForObject(getDiffControllerUri() + "diagnosis", DiagnosisDto.class);
        assertTrue(diagnosisDto.isEqual());
    }
}