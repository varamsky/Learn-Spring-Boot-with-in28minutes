package com.in28minutes.springboot.learnjpaandhibernate.course;

import com.in28minutes.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * When you have some code to run at the start of your application, you can implement the CommandLineRunner interface.
 */
@Component
public class CourseCommandLineRunner implements CommandLineRunner {
//    @Autowired
//    private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.insert(new Course(1, "Learn AWS", "in28minutes"));
//        repository.insert(new Course(2, "Learn Azure", "in28minutes"));
//        repository.insert(new Course(3, "Learn DevOps", "in28minutes"));
//        repository.insert(new Course(3, "Learn DevOps", "in28minutes"));

        repository.save(new Course(1, "Learn AWS", "in28minutes"));
        repository.save(new Course(2, "Learn Azure", "in28minutes"));
        repository.save(new Course(3, "Learn DevOps", "Ranga Karnam"));

        /*
        long vs Long

        - long is a primitive type which needs a value i.e., cannot be null
        - Long is an Object.
        - Long can be passed to a method that accepts an Object, Number, Long or long parameter (the last one thanks to auto-unboxing)
         */
        repository.deleteById(1L);

        System.out.println(repository.findById(2L));
        System.out.println(repository.findById(3L));

        System.out.println(repository.findAll());
        System.out.println(repository.count());

        System.out.println(repository.findByAuthor("Ranga Karnam"));

        System.out.println(repository.findByName("Learn Azure"));
    }
}
