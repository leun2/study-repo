package com.leun.test;

import org.springframework.stereotype.Repository;

@Repository
public class TestData {

    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};
    }
}
