package com.smartsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartsys.entities.Todo;

@Service
public interface TodoService {

    public List<Todo> findAll();
    public Todo createTodo(Todo todo);
    public Todo completeTodo(Long id);
    public Todo updateTodo(Long id, Todo todo);
    public void deleteTodo(Long id);

}
