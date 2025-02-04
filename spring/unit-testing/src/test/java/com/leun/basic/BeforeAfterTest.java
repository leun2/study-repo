package com.leun.basic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BeforeAfterTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

    @Test
    void tempTest() {
        System.out.println("temp test");
    }

    @Test
    void tempAnotherTest() {
        System.out.println("temp another test");
    }
}
