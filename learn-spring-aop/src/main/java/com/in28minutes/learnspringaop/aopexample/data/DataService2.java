package com.in28minutes.learnspringaop.aopexample.data;

import com.in28minutes.learnspringaop.aopexample.annotations.TrackTime;
import org.springframework.stereotype.Repository;

@Repository
public class DataService2 {

    @TrackTime
    public int[] retrieveData() {
        return new int[]{111, 222, 333, 444, 555};
    }
}
