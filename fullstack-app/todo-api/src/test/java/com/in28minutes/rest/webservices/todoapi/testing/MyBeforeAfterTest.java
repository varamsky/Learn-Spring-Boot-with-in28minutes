package com.in28minutes.rest.webservices.todoapi.testing;

import org.junit.jupiter.api.*;

/*
JUnit does not guarantee the orderly execution of the tests in the order that they are written here.
It might run test2() then test3() and then test1()
 */
public class MyBeforeAfterTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @Test
    void test3() {
        System.out.println("test3");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }
}
