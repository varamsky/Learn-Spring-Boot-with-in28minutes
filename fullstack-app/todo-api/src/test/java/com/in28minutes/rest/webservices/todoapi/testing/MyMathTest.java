package com.in28minutes.rest.webservices.todoapi.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMathTest {
    MyMath myMath = new MyMath();

    @Test
    void calculateSum_ThreeMemberArray() {
        int[] numbers = {1, 2, 3};
        MyMath myMath = new MyMath();

        int result = myMath.calculateSum(numbers);
        int expectedResult = 6;

        assertEquals(expectedResult, result);
    }

    @Test
    void calculateSum_ZeroLengthArray() {
        assertEquals(0, myMath.calculateSum(new int[]{}));
    }
}
