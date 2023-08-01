package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    /*
    Which versioning solution to consider?
    Here are some factors that you need to consider:-
    - URI pollution
        - URI versioning and Request Param versioning can lead to a number of URIs for the application.
        - In case of Custom header and Media Type versioning we can have same URI for different versions.
    - Misuse of HTTP headers
        - HTTP headers were never made to be used to manage versioning
    - Caching
        - For Custom header and Media Type versioning we need to have a look at both the URI and the request headers to implement caching.
    - Can we execute this from the browser?
        - Users might want to try out the API from the browser.
        - This is possible for URI versioning and Request Param versioning but not for Custom header and Media Type versioning.
    - API Documentation
        - Typical documentation builders do not work very well with header versioning techniques.

    Summary:
    - There is no perfect solution when it comes to versioning of an API.
    - As you see different companies(Twitter, Amazon, Microsoft, Github) use different versioning techniques.
    - You need to decide what suits your API.
     */

    // URI versioning(used by Twitter)
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // Request Parameter versioning(used by Amazon)
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // Custom Headers versioning(used by Microsoft)
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // Media Type versioning or content negotiation or accept header(used by Github)
    @GetMapping(value = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
