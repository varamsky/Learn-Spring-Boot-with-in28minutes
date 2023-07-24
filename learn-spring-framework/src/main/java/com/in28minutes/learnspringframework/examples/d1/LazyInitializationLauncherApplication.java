package com.in28minutes.learnspringframework.examples.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA{

}

/**
 * There are 2 types of initialization in Spring:-
 * 1. Eager
 * 2. Lazy
 *
 * By default, all Beans are initialized as Eager, i.e. at the start of the Spring Application.
 * Even if we are not using the Bean anywhere in the code, it will be initialized.
 * You can test this by removing @Lazy annotation and not calling ClassB in the main() method
 * You will find that even then the ClassB Bean is initialized and the sysout in its constructor is run.
 *
 * With @Lazy annotation, the Bean is initialized only when it is used somewhere in the code.
 * But, this needs to be used with caution as any error with the configuration will not be shown at startup of the Spring application
 * unless the Lazy Bean is called while using/running the application.
 * Thus, @Lazy should not be used frequently.
 *
 * Lazy initialization can be used with Components and Beans.
 * Also, if you used @Lazy with @Configuration then all the Beans in the Configuration class with use Lazy initialization.
 */
@Component
@Lazy
class ClassB{
    private ClassA classA;

    public ClassB(ClassA classA){
        System.out.println("Some initialization logic in ClassB constructor");
        this.classA = classA;
    }

    public void doSomething(){
        System.out.println("Do Something");
    }
}

@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)) {
            System.out.println("Initialization of context is completed");

            context.getBean(ClassB.class).doSomething();
        }
    }
}
