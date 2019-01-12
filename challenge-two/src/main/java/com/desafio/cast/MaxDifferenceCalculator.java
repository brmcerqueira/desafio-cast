package com.desafio.cast;

public class MaxDifferenceCalculator {
    public static int maxDifference(int[] numbers) {
        int max = -1;

        if (numbers.length > 1) {
            for (int current = 1; current < numbers.length; current++) {
                for (int anyOlder = 0; anyOlder < current; anyOlder++) {
                    if (numbers[anyOlder] < numbers[current]) {
                        int value = numbers[current] - numbers[anyOlder];
                        if (value > max) {
                            max = value;
                        }
                    }
                }
            }
        }

        return max;
    }
}