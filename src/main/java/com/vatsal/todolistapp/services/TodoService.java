package com.vatsal.todolistapp.services;

import com.vatsal.todolistapp.repository.TodoRepository;
import com.vatsal.todolistapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo findTodoByID(Long id){
        return (Todo) todoRepository.findById(id).orElse(null);
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo){
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if(optionalTodo.isPresent()){
            Todo existingTodo = optionalTodo.get();
            existingTodo.setId(updatedTodo.getId());
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.getCompleted());
            return todoRepository.save(existingTodo);
        }else {
            return null;
        }
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

    public List<Todo> getToDosByStatus(boolean completed){
        return todoRepository.findByCompleted(completed);
    }

}
