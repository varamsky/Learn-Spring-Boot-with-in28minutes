package com.in28minutes.learnspringaop.aopexample.business;

import com.in28minutes.learnspringaop.aopexample.annotations.TrackTime;
import com.in28minutes.learnspringaop.aopexample.data.DataService1;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService1 {
    private DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }

    @TrackTime
    public int calculateMax() {
        int[] data = dataService1.retrieveData();

        /*
        Uncomment the throw exception line to check the working of LoggingAspect logMethodCallAfterThrowing()
         */
//        throw new RuntimeException("Something went wrong");
        return Arrays.stream(data).max().orElse(0);
    }
}
