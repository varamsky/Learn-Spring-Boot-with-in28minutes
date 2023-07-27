package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    /*
    If you want to initialize a static variable you need to create a static block.
    Static block code executes only once during the class loading.
     */
    static {
        todos.add(new Todo(++todosCount, "in28minutes", "Learn AWS 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps 1", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack development 1", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        /*
        This is a shortcut method to delete the existing todo and adding a new one with the updated data.
        Actually, we should update the required data one by one in the original todo.
         */
        deleteById(todo.getId());
        todos.add(todo);
    }
}
