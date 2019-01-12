package com.desafio.cast;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping(value="/v1/diff")
public class DiffController {

    private static String left = null;
    private static String right = null;

    @RequestMapping(value="/{id}/left", method = RequestMethod.HEAD)
    public void left(@PathVariable String id) {
        left = id;
    }

    @RequestMapping(value="/{id}/right", method = RequestMethod.HEAD)
    public void right(@PathVariable String id) {
        right = id;
    }

    @RequestMapping(value="/diagnosis", method = RequestMethod.GET)
    public DiagnosisDto diagnosis() {
        Base64.Decoder decoder = Base64.getDecoder();
        String rawLeft = new String(decoder.decode(left));
        String rawRight = new String(decoder.decode(right));

        DiagnosisDto diagnosisDto = new DiagnosisDto();
        diagnosisDto.setEqual(rawLeft.equals(rawRight));
        return diagnosisDto;
    }
}
