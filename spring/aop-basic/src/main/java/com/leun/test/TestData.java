package com.leun.test;

import com.leun.aop.TrackTime;
import org.springframework.stereotype.Repository;

@Repository
public class TestData {

    @TrackTime
    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};
    }
}
