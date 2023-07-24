package com.in28minutes.learnspringframework.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class YourBusinessClass {
    /*
    Types of Dependency Injection
    1. Constructor-based Injection
    2. Setter-based Injection
    3. Field-based Injection(Dependency is injected using Reflection)
     */

    @Autowired // This is called Field Injection
    Dependency1 dependency1;

    Dependency2 dependency2;

    /*
     This is called Constructor Injection
     @Autowired is optional for Constructor Injection

     NOTE: Spring Team recommends Constructor-based Injection as dependencies are automatically set when the object is created
     (Though this is mentioned on Spring docs - https://docs.spring.io/spring-framework/docs/3.0.0.M4/reference/html/ch03s04.html
     "Some purists favor constructor-based injection.
     Supplying all object dependencies means that the object is always returned to client (calling) code in a totally initialized state.
     The disadvantage is that the object becomes less amenable to reconfiguration and re-injection.")
     */
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        System.out.println("Constructor Injection");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    public void setDependency1(Dependency1 dependency1) {
        this.dependency1 = dependency1;
    }

    @Autowired // This is called Setter Injection
    public void setDependency2(Dependency2 dependency2) {
        System.out.println("Setter Injection - Dependency2");
        this.dependency2 = dependency2;
    }

    public String toString() {
        return "Using " + dependency1 + " and " + dependency2;
    }
}

@Component
class Dependency1 {

}

@Component
class Dependency2 {

}

@Configuration
@ComponentScan // not specifying any specific package will ComponentScan in the current package and its sub-packages
public class DepInjectionLauncherApplication {

    /*
    What is Inversion of Control(IOC)?
    In the previous versions/commits of this project the programmer was doing all the work of creating object and wiring them wherever needed.

    But, now all the work is done by Spring. Creation of Beans, managing the objects, handling dependencies and auto-wiring them.
    Thus, the control has shifted from the programmer to Spring of managing the application, all we need to do is specify "what is to be done" and Spring does it for us.

    This is called Inversion of Control.
     */

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(YourBusinessClass.class));
        }
    }
}
