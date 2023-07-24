package com.in28minutes.learnspringframework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.game")
// With @ComponentScan annotation, Spring looks for and finds the PacmanGame(with @Component annotation) and auto-wire it where needed.
// NOTE: @ComponentScan annotation is needed even if the Component belongs to the same package
public class GamingAppLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {
            // GamingConsole is an interface. Therefore, it will look for classes that implement the GamingConsole interface
            context.getBean(GamingConsole.class).up(); // Here, it will look for @Primary and run the MarioGame

            context.getBean(GameRunner.class).run(); // Whereas, here it will look for @Qualifier and run SuperContraGame because @Qualifier is added in the constructor of the GameRunner class
        }
    }
}
