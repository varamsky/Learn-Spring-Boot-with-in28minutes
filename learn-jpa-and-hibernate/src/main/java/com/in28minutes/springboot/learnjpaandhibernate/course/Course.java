package com.in28minutes.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
// if the name of the Entity and the table in the database do not match, we can add a name attribute as @Entity(name="database_table_name")
public class Course {
    @Id
    private long id;
    /*
    Although here we are explicitly providing the @Column annotation because we have the field name same here as in the database table
    we can omit adding the @Column as in the case of the field author below
     */
    @Column(name = "name")
    private String name;
    private String author;

    public Course() {
    }

    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
