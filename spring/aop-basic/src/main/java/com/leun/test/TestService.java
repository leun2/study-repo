package com.leun.test;

import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestData testData;

    public TestService(TestData testData) {
        this.testData = testData;
    }

    public int calculateMax() {
        int[] data = testData.retrieveData();
        return Arrays.stream(data).max().orElse(0);
    }
}
