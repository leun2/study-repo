package com.leun.basic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AssertTest {

    List<String> Users = Arrays.asList("Lee", "Kim", "Joo");

    @Test
    void userTest() {

        assertTrue(Users.contains("Lee"));
        assertFalse(Users.contains("Woo"));
        assertEquals(3, Users.size());
    }

    @Test
    void arrayTest() {
        assertArrayEquals(new int[] {1, 2}, new int[] {2, 1});
    }
}
