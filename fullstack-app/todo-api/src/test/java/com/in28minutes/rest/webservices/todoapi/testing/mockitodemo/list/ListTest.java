package com.in28minutes.rest.webservices.todoapi.testing.mockitodemo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void simpleTest() {
        List listMock = mock(List.class);

        when(listMock.size()).thenReturn(3);

        assertEquals(3, listMock.size());
    }

    @Test
    public void multipleReturns() {
        List listMock = mock(List.class);

        when(listMock.size()).thenReturn(1).thenReturn(2);

        assertEquals(1, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size()); // After second time it will default to the last returned value
        assertEquals(2, listMock.size());
    }

    @Test
    public void specificParameters() {
        List listMock = mock(List.class);

        when(listMock.get(0)).thenReturn("SomeString");

        assertEquals("SomeString", listMock.get(0));
        assertNull(listMock.get(1)); // By default, mockito will return null
    }

    @Test
    public void genericParameters() {
        List listMock = mock(List.class);

        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");

        assertEquals("SomeOtherString", listMock.get(0));
        assertEquals("SomeOtherString", listMock.get(1));
    }
}
