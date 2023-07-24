package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

/**
 * @Component Indicates that an annotated class is a "component".
 * Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.
 *
 * By adding this annotation, an instance of this PacmanGame class as autowired into the GamingAppLauncherApplication class
 */
@Component
public class PacmanGame implements GamingConsole {
    public void up(){
        System.out.println("up");
    }

    public void down(){
        System.out.println("down");
    }

    public void left(){
        System.out.println("left");
    }

    public void right(){
        System.out.println("right");
    }
}
