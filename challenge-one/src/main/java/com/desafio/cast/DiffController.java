package com.desafio.cast;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping(value="/v1/diff")
public class DiffController {

    private static String left = null;
    private static String right = null;

    @RequestMapping(value="/clear", method = RequestMethod.HEAD)
    public void clear() {
        left = null;
        right = null;
    }

    @RequestMapping(value="/{id}/left", method = RequestMethod.HEAD)
    public void left(@PathVariable String id) {
        left = id;
    }

    @RequestMapping(value="/{id}/right", method = RequestMethod.HEAD)
    public void right(@PathVariable String id) {
        right = id;
    }

    @RequestMapping(value="/diagnosis", method = RequestMethod.GET)
    public Object diagnosis() throws Exception {
        if (left == null) {
            throw new Exception("The 'left' parameter was not reported.");
        }

        if (right == null) {
            throw new Exception("The 'right' parameter was not reported.");
        }

        Base64.Decoder decoder = Base64.getDecoder();
        String rawLeft = new String(decoder.decode(left));
        String rawRight = new String(decoder.decode(right));

        if (rawLeft.equals(rawRight)) {
            return true;
        }
        else if (rawLeft.length() != rawRight.length()) {
            if (rawLeft.length() > rawRight.length()) {
                return rawLeft.length() - rawRight.length();
            }
            else {
                return rawRight.length() - rawLeft.length();
            }
        }
        else {
            ArrayList<DiffDto> result = new ArrayList<>();

            DiffDto diffDto = new DiffDto();

            for (int index = 0; index < rawLeft.length(); index++) {
                if (rawLeft.charAt(index) != rawRight.charAt(index)) {
                    if (diffDto.getOffset() == 0) {
                        diffDto.setOffset(index);
                    }
                }
                else if (diffDto.getOffset() > 0) {
                    diffDto.setLength(index - diffDto.getOffset());
                    result.add(diffDto);
                    diffDto = new DiffDto();
                }
            }

            return result;
        }
    }
}
