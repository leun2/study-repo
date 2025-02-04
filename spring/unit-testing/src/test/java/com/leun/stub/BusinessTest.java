package com.leun.stub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTest {

    @Test
    void findMaxValueTest() {
        BusinessService businessServiceStub = new BusinessServiceStub();

        BusinessImpl business = new BusinessImpl(businessServiceStub);
        int result = business.findMaxValue();

        assertEquals(99, result);
    }
}

class BusinessServiceStub implements BusinessService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {25, 60, 1, 3, 14, 99};
    }
}
