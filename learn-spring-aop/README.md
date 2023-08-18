## Learn Spring AOP

### What is AOP?

- Aspect Oriented Programming

### Spring AOP

1. Implement the cross-cutting concern as an aspect
2. Define point cuts to indicate where the aspect should be applied

- Two Popular AOP Frameworks:-
    - Spring AOP
        - Not a complete AOP solution but very popular
        - Only works with Spring Beans
        - Example: Intercept method calls to Spring Beans
    - AspectJ
        - Complete AOP solution but rarely used
        - Example: Intercept any method call on any Java class
        - Example: Intercept changes of values in a field
- We will mainly focus on Spring AOP

### AOP - Important Terminology

- Compile Time
    - Advice - What code to execute?
        - Example: Logging, Authentication
    - Pointcut - Expression that identifies method calls to be intercepted
        - Example: `execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")`
    - Aspect - A combination of
        - Advice - What to do?
        - Pointcut - When to intercept a method call?
        - Therefore, Aspect = WHAT + WHEN?
    - Weaver - Weaver is the framework that implements AOP
        - Example: AspectJ or Spring AOP
- Runtime
    - Join Point - When pointcut condition is true, the advice is executed. A specific execution instance of an advice
      is called a JoinPoint.

### Spring AOP - Important Annotations

- `@Before` - Do something before a method is called
- `@After` - Do something after a method is executed irrespective of whether:
    - Method executes successfully or,
    - Method throws an exception
- `@AfterReturning` - Do something ONLY when a method executes successfully
- `@AfterThrowing` - Do something ONLY when a method throws an exception
- `@Around` - Do something before and after a method execution
