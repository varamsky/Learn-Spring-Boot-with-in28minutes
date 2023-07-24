package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    /**
     * Loosely coupled code
     * With the GamingConsole Interface we have loosely coupled the GameRunner class and the MarioGame, SuperContraGame and PacmanGame classes
     * Now, in the AppGamingBasicJava.java if we want to change the game from MarioGame to another other game and vice versa
     * We will need to change only the object in the AppGamingBasicJava.java
     * No changes will be needed in the GameRunner class
     * This solves the problem and minimizes the number of code changes needed for a very simple objective of "changing the game"
     * Therefore, this code is loosely coupled.
     */

    private GamingConsole game;

    /**
     * @Primary vs @Qualifier, which one to use when?
     * Look at this small example
     *
     * @Component @Primary
     * class QuickSort implement SortingAlgorithm{}
     *
     * @Component
     * class BubbleSort implement SortingAlgorithm{}
     *
     * @Component @Qualifier("RadixSortQualifier")
     * class RadixSort implement SortingAlgorithm{}
     *
     * @Component
     * class ComplexAlgorithm
     * @Autowired
     * private SortingAlgorithm algorithm;
     *
     * @Component
     * class AnotherComplexAlgorithm
     * @Autowired @Qualifier("RadixSortQualifier")
     * private SortingAlgorithm iWantToUseRadixSortOnly;
     *
     * @Primary - says that give me a Sorting Algorithm, I don't care which one (NOTE that although we know that we have added the @Primary to QuickSort, this is not known to ComplexAlgorithm. Therefore, it just asks for a Sorting Algorithm)
     * @Qualifier - whereas, this says that give me this SPECIFIC algorithm, I don't want any other
     *
     * @Qualifier has higher priority than @Primary
     *
     * NOTE: ALWAYS THINK FROM THIS PERSPECTIVE WHEN USING @Primary vs @Qualifier
     *
     * If the @Qualifier was not added to the SuperContraGame then we could also use the class name(with the first letter in small case) as shown below
     * public GameRunner(@Qualifier("superContraGame") GamingConsole game) {
     */
    public GameRunner(@Qualifier("SuperContraGameQualifier") GamingConsole game) { // This is an example of Constructor based Dependency Injection
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + game);

        game.up();
        game.down();
        game.left();
        game.right();
    }
}
