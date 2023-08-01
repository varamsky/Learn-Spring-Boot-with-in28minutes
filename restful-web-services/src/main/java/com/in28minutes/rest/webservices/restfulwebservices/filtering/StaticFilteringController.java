package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StaticFilteringController {

    @GetMapping("static-filtering")
    public SomeBeanForStaticFiltering filtering() {
        return new SomeBeanForStaticFiltering("value1", "value2", "value3");
    }

    @GetMapping("static-filtering-list")
    public List<SomeBeanForStaticFiltering> filteringList() {
        return Arrays.asList(new SomeBeanForStaticFiltering("value1", "value2", "value3"), new SomeBeanForStaticFiltering("value4", "value5", "value6"));
    }
}
