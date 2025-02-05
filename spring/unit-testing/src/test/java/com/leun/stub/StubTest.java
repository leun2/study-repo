package com.leun.stub;

import com.leun.DataService;
import com.leun.MaxValueFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StubTest {

    @Test
    void findMaxValueTest() {
        DataService serviceStub = new ServiceStub();

        MaxValueFinder business = new MaxValueFinder(serviceStub);
        int result = business.findMaxValue();

        assertEquals(99, result);
    }
}

class ServiceStub implements DataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {25, 60, 1, 3, 14, 99};
    }
}
