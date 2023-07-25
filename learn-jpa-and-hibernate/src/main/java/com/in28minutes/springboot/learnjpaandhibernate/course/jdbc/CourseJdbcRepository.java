package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    /*
    In terms of complexity,
    JDBC < SpringJDBC < Spring Data JPA

    Here, demonstrate the SpringJDBC(we do not get into JDBC because it needs even more code)

    With SpringJDBC we can reduce the Java code as compared to simple JDBC but, we still have to write our own SQL queries.

    This SpringJDBC code might look simple as we are dealing with a 1 simple table.
    But, as our database grows in complexity it will be very difficult to handle because of writing the SQL queries explicitly.

    Therefore, we will you Spring Data JPA.
     */
    @Autowired
    private JdbcTemplate springJdbcTemplate;
    private static final String INSERT_QUERY =
            """
                    insert into course(id, name, author)
                    values(?,?,?);
                    """;
    private static final String DELETE_QUERY =
            """
                    delete from course
                    where id=?
                    """;
    private static final String SELECT_QUERY =
            """
                    select * from course
                    where id=?
                    """;

    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course findById(long id) {
        // map ResultSet to Bean
        return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
    }
}
