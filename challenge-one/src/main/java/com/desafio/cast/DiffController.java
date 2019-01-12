package com.desafio.cast;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        DiagnosisDto diagnosisDto = new DiagnosisDto();
        diagnosisDto.setEqual(left.equals(right));
        return diagnosisDto;
    }
}
