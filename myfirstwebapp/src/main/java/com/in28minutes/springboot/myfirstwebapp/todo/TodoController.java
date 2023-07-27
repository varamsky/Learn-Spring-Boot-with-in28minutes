package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("in28minutes");
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = (String) model.get("name");
        // setting default values when adding a new item
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        // The Todo object is added here as a Command Bean(Form Backing Object) the same is added as modelAttribute="todo" in the jsp so that the bean value can be used there
        // This is 2-way binding:
        // From the Todo object in the showNewTodoPage to the form in the browser
        // And from the form in the browser to the Todo object in the this method

        if (result.hasErrors()) {
            /*
            BindingResult gets any errors from the form and we can use it here to act accordingly.
            For example, we can se that we remain in the same page i.e., todo.jsp by returning todo from here.
            And the todo.jsp handles the error by showing it as a warning with the <form:errors> tag.
             */
            return "todo";
        }

        /*
        Model contains name because we are using name as a Session Attribute:
        When a user logs in, name is set into session.
         */
        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(),
                todo.getTargetDate(), false);

        // We need to redirect to list-todos URL else we will have to re-populate the todos here again and that will lead to unnecessary code duplication.
//        return "listTodos";
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = (String) model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);

        return "redirect:list-todos";
    }

}
