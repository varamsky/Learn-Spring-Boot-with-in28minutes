package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Component is a generic annotation applicable for any class
 * It is the base for all Stereotype Annotations
 *
 * Specializations of @Component:-
 * - @Service -> Indicates that an annotated class has business logic
 * - @Controller -> Indicates that annotated class is a "Controller"(e.g. a web controller)
 * - @Repository -> Indicates that annotated class is used to retrieve and/or manipulate data in a database
 *
 * It is recommended to use the most specific annotation possible because,
 * - By using a specific annotation, you are giving more information to the framework about your intentions
 * - You can use AOP to add additional behaviour
 *      - e.g. for @Repository, Spring automatically wires in JDBC Exception translation features
 */
//@Component
@Service
public class BusinessCalculationService {
    private final DataService dataService;

    public BusinessCalculationService(DataService dataService){
        super();
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveDate()).max().orElse(0);
    }
}
