package com.leun;


public class MaxValueFinder {

    private final DataService dataService;

    public MaxValueFinder(DataService dataService) {
        this.dataService = dataService;
    }

    public int findMaxValue() {
        int[] retrievedData = dataService.retrieveAllData();
        int maxValue = Integer.MIN_VALUE;
        for (int value : retrievedData) {
            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }
}
