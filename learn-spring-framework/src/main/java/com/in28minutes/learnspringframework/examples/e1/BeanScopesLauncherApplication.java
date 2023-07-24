package com.in28minutes.learnspringframework.examples.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass{

}

/**
 * There are 2 types of Scopes for Spring Beans:-
 * 1. Singleton
 * 2. Prototype
 *
 * Singleton is the default scope used for Spring Beans.
 * When a Spring Bean has scope as Singleton then only 1 instance of that Bean/object will be used throughout the Spring Container.
 *
 * When the Prototype scope is used, every time an instance of the Bean is required a new instance of the Bean will be created.
 * Therefore, possibly many instances of such a Bean will be used by the Spring Container.
 *
 * Singleton is the most frequently used Bean Scope.
 * Prototype scope is rarely used.
 *
 * Singleton is used in case of Stateless Beans.
 * Prototype can be used in the case of Stateful Beans.
 *
 * In the code below you will notice that  every time Normal Class Bean is called for, the same instance(with the same object hashcode) is sent.
 * Whereas, every time Prototype Class is used a new instance(with different object hashcode) is used.
 *
 * NOTE: Spring Singleton is a little different from the normal Java Singleton(mentioned in the GOF Design Patterns Book)
 * Spring Singleton - One object instance every Spring IoC Container(one might use multiple Containers in an application i.e, multiple Containers in a JVM -> this is not done usually)
 * Java Singleton - One object instance per JVM
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass{

}

@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }
    }
}
