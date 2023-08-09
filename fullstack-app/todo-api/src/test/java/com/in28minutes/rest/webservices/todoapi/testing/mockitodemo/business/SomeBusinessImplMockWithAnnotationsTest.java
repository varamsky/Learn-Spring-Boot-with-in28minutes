package com.in28minutes.rest.webservices.todoapi.testing.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
Need this annotation to let Spring know that we are mocking classes here.

As you can see, using Mockito with Annotations makes it easier to write, test and maintain the tests.
 */
@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplMockWithAnnotationsTest {

    // This creates a mock
    @Mock
    private DataService dataServiceMock;

    // The mock is injected into the instance of SomeBusinessImpl
    @InjectMocks
    private SomeBusinessImpl businessImpl;

    @Test
    void findTheGreatestFromAllData_basicScenario() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

        assertEquals(25, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{35});

        assertEquals(35, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_emptyArray() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});

        assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
    }
}