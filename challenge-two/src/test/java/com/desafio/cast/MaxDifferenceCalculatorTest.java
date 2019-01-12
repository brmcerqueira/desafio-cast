package com.desafio.cast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MaxDifferenceCalculatorTest{

    @Test
    public void sampleInputIntroduction() {
        assertEquals(5, MaxDifferenceCalculator.maxDifference(new int[] {
            1,
            2,
            6,
            4
        }));
    }

    @Test
    public void sampleInput0() {
       assertEquals(8, MaxDifferenceCalculator.maxDifference(new int[] {
           2,
           3,
           10,
           2,
           4,
           8,
           1
       }));
    }

    @Test
    public void sampleInput1() {
        assertEquals(2, MaxDifferenceCalculator.maxDifference(new int[] {
            7,
            9,
            5,
            6,
            3,
            2
        }));
    }
}
