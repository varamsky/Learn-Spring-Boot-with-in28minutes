package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
If we extended Exception then it will create a checked Exception and, we should avoid checked Exceptions when creating a custom Exception.
But what is checked Exception?
- Have a look at this - https://www.baeldung.com/java-checked-unchecked-exceptions
- Java exceptions fall into two main categories: checked exceptions and unchecked exceptions.
- In general, checked exceptions represent errors outside the control of the program.
- For example, the constructor of FileInputStream throws FileNotFoundException if the input file does not exist.
- Java verifies checked exceptions at compile-time.
- Therefore, we should use the throws keyword to declare a checked exception.
- We can also use a try-catch block to handle a checked exception.
- The Exception class is the superclass of checked exceptions, so we can create a custom checked exception by extending Exception.

And what is an unchecked Exception?
- Java does not verify unchecked exceptions at compile-time.
- Furthermore, we don't have to declare unchecked exceptions in a method with the "throws" keyword.
- The RuntimeException class is the superclass of all unchecked exceptions, so we can create a custom unchecked exception by extending RuntimeException.

When to use Checked Exceptions and Unchecked Exceptions?
- The Oracle Java Documentation provides guidance on when to use checked exceptions and unchecked exceptions:
- “If a client can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception.”
- For example, before we open a file, we can first validate the input file name. If the user input file name is invalid, we can throw a custom checked exception.
- In this way, we can recover the system by accepting another user input file name.
- However, if the input file name is a null pointer or, it is an empty string, it means that we have some errors in the code. In this case, we should throw an unchecked exception.

And why should we avoid it?
- Best Practices for Custom Exceptions:- Prefer runtime exceptions over checked exceptions.
- If the exception is recoverable - it should be checked. If the exception is not recoverable and the program must halt - it should be unchecked.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    /*
    This will display a lot of trace logs if devtools is included in the pom.xml and, it is run in the dev environment.
    However, in the production environment when you use the JAR file to run the application the devtools in the pom.xml will be ignored and the error logs won't be returned with the Exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
