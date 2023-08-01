package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DynamicFilteringController {
    /*
    For static filtering, we apply filter details/annotations in the Bean itself.
    But, in the case of dynamic filtering as we can have different filtering logic in different APIs for the same Bean, we need to add the filtering logic in the API code itself.
     */

    @GetMapping("dynamic-filtering")
    public MappingJacksonValue filtering() {
        SomeBeanForDynamicFiltering someBeanForDynamicFiltering = new SomeBeanForDynamicFiltering("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanForDynamicFiltering);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanId", filter);
        mappingJacksonValue.setFilters(filterProvider);
        /*
        In addition to all this code you need to add @JsonFilter("FILTER_ID(in this case SomeBeanFilter)") to the Bean class
         */

        return mappingJacksonValue;
    }

    @GetMapping("dynamic-filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBeanForDynamicFiltering> list = Arrays.asList(new SomeBeanForDynamicFiltering("value1", "value2", "value3"), new SomeBeanForDynamicFiltering("value4", "value5", "value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        // The id mentioned here is not the ID of the filter but actually -> The id to associate the filter with i.e., the id of the Bean.
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanId", filter);
        mappingJacksonValue.setFilters(filterProvider);
        /*
        In addition to all this code you need to add @JsonFilter("FILTER_ID(in this case SomeBeanFilter)") to the Bean class
         */

        return mappingJacksonValue;
    }
}
