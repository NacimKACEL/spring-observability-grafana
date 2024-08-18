package com.smartsys.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartsys.entities.Todo;
import com.smartsys.service.TodoService;

@RestController
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping("/create-todo")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PatchMapping("todos/{id}/complete")
    public Todo completeTodoById(@PathVariable("id") final Long id) {
        return todoService.completeTodo(id);
    }

    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("todos/{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
    }

}
