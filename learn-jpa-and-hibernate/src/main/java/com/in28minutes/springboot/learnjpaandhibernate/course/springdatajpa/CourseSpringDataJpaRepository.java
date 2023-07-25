package com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
With Spring Data JPA we need to write even lesser code as compared to JPA.
Here, we don't even talk to the EntityManager directly, everything is done by the methods provided by JpaRepository.

All we need to do is create this interface(and then use its method for example in CourseCommandLineRunner run())


Hibernate vs JPA

JPA defines a specification. It is an API.
It specifies:-
- How do you define entities?
- How do you map attributes?
- Who manages the entities?

Hibernate is one of the popular implementations of JPA.

Using Hibernate directly would result in a lock in to Hibernate
- There are other implementations as well for example, TopLink

Therefore, always make use of JPA.
Once you have used JPA in your code you can use Hibernate(or any other implementation of JPA) as your implementation.
 */
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    /*
    We need to follow some predefined conventions so that all we do is define a method here and JpaRepository will implement it for us.
    For naming convention check this - https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.sample-app.finders.strategies
     */
    List<Course> findByAuthor(String author); // all we do is declare this method and implementation is provided by JpaRepository due to naming convention.

    List<Course> findByName(String name);
}
