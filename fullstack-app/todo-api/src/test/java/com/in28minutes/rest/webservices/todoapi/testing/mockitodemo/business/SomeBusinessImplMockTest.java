package com.in28minutes.rest.webservices.todoapi.testing.mockitodemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessImplMockTest {
    @Test
    void findTheGreatestFromAllData_basicScenario() {
        DataService dataServiceMock = mock(DataService.class); // Here we didn't have to create a stub implementation on our own nor did we need to provide an instance of the class. Mockito mocked it for us.
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
        assertEquals(25, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_oneValue() {
        DataService dataServiceMock = mock(DataService.class); // Here we didn't have to create a stub implementation on our own nor did we need to provide an instance of the class. Mockito mocked it for us.
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{35});

        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
        assertEquals(35, businessImpl.findTheGreatestFromAllData());
    }
}