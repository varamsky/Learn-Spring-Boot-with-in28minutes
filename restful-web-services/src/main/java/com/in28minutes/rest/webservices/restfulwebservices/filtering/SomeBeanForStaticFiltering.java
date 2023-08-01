package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
This can apply filtering of properties on class-level.
But, for this you need to update the name of the field in this list as well if that is changed in the actual declaration.
 */
//@JsonIgnoreProperties({"field1", "field2"})
public class SomeBeanForStaticFiltering {
    private String field1;
    @JsonIgnore
    // This provides static filtering of an object property. Therefore, field2 will not be sent as a part of the API response.
    private String field2;
    private String field3;

    public SomeBeanForStaticFiltering(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
