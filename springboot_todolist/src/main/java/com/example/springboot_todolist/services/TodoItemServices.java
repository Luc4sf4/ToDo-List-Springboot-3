package com.example.springboot_todolist.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot_todolist.repositories.TodoItemRepository;
import com.example.springboot_todolist.models.TodoItem;


@Service
public class TodoItemServices {
    
    @Autowired
    private TodoItemRepository repository;


    public Iterable<TodoItem> getAll() {
        return repository.findAll();
    }

    public Optional<TodoItem> getById(Long id){

        return  repository.findById(id);

    }

    public TodoItem save(TodoItem item){

        if(item.getId()==null){
            item.setCreatedAt(Instant.now());
        }

        item.setUpdatedAt(Instant.now());
        return repository.save(item);

    }

    public void delete(TodoItem itemDelete){
        repository.delete(itemDelete);

    }

}
