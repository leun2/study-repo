package com.leun.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.leun.DataService;
import com.leun.MaxValueFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    private DataService dataServiceMock;

    @InjectMocks
    private MaxValueFinder maxValueFinder;

    @Test
    void findMaxValueTestByArray() {

//        DataService dataServiceMock = mock(DataService.class);
//        MaxValueFinder maxValueFinder = new MaxValueFinder(dataServiceMock);

        when(dataServiceMock.retrieveAllData())
            .thenReturn(new int[] { 1, 2, 3, 4, 6, 5 });

        assertEquals(6, maxValueFinder.findMaxValue());
    }

    @Test
    void findMaxValueTestByEmptyArray() {
        when(dataServiceMock.retrieveAllData())
            .thenReturn(new int[] { });

        assertEquals(Integer.MIN_VALUE, maxValueFinder.findMaxValue());
    }
}
