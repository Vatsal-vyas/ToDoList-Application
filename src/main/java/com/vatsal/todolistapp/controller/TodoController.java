package com.vatsal.todolistapp.controller;

import com.vatsal.todolistapp.model.Todo;
import com.vatsal.todolistapp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id){
        return todoService.findTodoByID(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedtodo){
        return todoService.updateTodo(id, updatedtodo);
    }

    @GetMapping("/status/{completed}")
    public List<Todo> findByStatus(@PathVariable boolean completed){
        return todoService.getToDosByStatus(completed);
    }
}
