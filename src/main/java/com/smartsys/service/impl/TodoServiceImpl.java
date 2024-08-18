package com.smartsys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smartsys.entities.Todo;
import com.smartsys.repository.TodoRepository;
import com.smartsys.service.TodoService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private Counter todoCounter;
    private MeterRegistry meterRegistry;

    public TodoServiceImpl(MeterRegistry meterRegistry, TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        this.meterRegistry = meterRegistry;
        this.todoCounter = Counter.builder("todo.created.count")
                .tags("status", "created")
                .description("Total number of todo items created")
                .register(meterRegistry);
        
        long pendingTodoCount = todoRepository.countByCompleted(false);
        Tags tags = Tags.of("status", "pending");
        meterRegistry.gauge("todo.pending.count", tags, pendingTodoCount);
    }

    @Override
    public List<Todo> findAll() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sortByCreatedAtDesc);
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setCompleted(false);
        todo.setCreatedAt(new Date());
        Todo createdTodo = todoRepository.save(todo);
        incrementTodoCreatedCounter();
        return createdTodo;
    }

    @Override
    public Todo completeTodo(Long id) {
        return todoRepository.findById(id).map(todoData -> {
            todoData.setCompleted(true);
            Todo updatedTodo = todoRepository.save(todoData);
            return updatedTodo;
        }).orElse(null);
    }

    public Todo updateTodo(Long id, Todo todo) {
        return todoRepository.findById(id).map(todoData -> {
            todoData.setTitle(todo.getTitle());
            todoData.setCompleted(todo.isCompleted());
            Todo updatedTodo = todoRepository.save(todoData);
            return updatedTodo;
        }).orElse(null);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 3000)
    public void updatePendingTodoMetrics() {
        long pendingTodoCount = todoRepository.countByCompleted(false);
        Tags tags = Tags.of("status", "pending");
        meterRegistry.gauge("todo.pending.count", tags, pendingTodoCount);
    }

    public void incrementTodoCreatedCounter() {
        todoCounter.increment();
    }

}
