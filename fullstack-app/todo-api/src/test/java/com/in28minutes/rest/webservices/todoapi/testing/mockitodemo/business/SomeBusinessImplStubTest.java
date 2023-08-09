package com.in28minutes.rest.webservices.todoapi.testing.mockitodemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeBusinessImplStubTest {
    @Test
    void findTheGreatestFromAllData_basicScenario() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
        int result = businessImpl.findTheGreatestFromAllData();
        assertEquals(25, result);
    }

    @Test
    void findTheGreatestFromAllData_OneValue() {
        DataServiceStub1 dataServiceStub1 = new DataServiceStub1();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub1);
        int result = businessImpl.findTheGreatestFromAllData();
        assertEquals(35, result);
    }
}

/*
Here, you can see that we have to create a separate test implementation of the DataService interface as a stub.
This works fine but is difficult to test different scenarios.

For different scenarios we will need to write different implementations and that can be difficult to maintain.
Because, if a new method is added to the interface then we will need to implement that here as well even though we might not need it for this specific test.
 */
class DataServiceStub implements DataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{25, 15, 5};
    }
}

class DataServiceStub1 implements DataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{35};
    }
}
