package com.leun.stub;

public class BusinessImpl {

    private final BusinessService businessService;

    public BusinessImpl(BusinessService businessService) {
        this.businessService = businessService;
    }

    public int findMaxValue() {
        int[] retrievedData = businessService.retrieveAllData();
        int maxValue = Integer.MIN_VALUE;
        for (int value : retrievedData) {
            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }
}
