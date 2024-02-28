package com.example.springboot_todolist.Controllers;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springboot_todolist.models.TodoItem;
import com.example.springboot_todolist.services.TodoItemServices;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class TodoFormController {


        @Autowired
        private TodoItemServices todoItemServices;

        @GetMapping("/create-todo")
        public String showCreateFormm(TodoItem todoItem) {
            return "new-todo-item";
        }

        @PostMapping("/todo")
        public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {


            TodoItem item = new TodoItem();
            item.setDescription(todoItem.getDescription());
            item.setIsComplete(todoItem.getIsComplete());
            
            todoItemServices.save(todoItem);

            return "redirect:/";
        }
        
        @GetMapping("/delete/{id}")
        public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
            TodoItem todoItem = todoItemServices
                                .getById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + "not foud"));

            todoItemServices.delete(todoItem);

            return "redirect:/";
            
        }
        
        @GetMapping("/edit/{id}")
        public String showUpdateForm(@PathVariable("id") Long id, Model model) {
            TodoItem todoItem = todoItemServices
                                .getById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + "not foud"));
            model.addAttribute("todo", todoItem);

            return "edit-todo-item";
        }
        
        @PostMapping("/todo/{id}")
        public String updateTodoItem(@PathVariable("id") Long id,@Valid TodoItem todoItem,BindingResult result,Model model) {
            TodoItem item = todoItemServices
            .getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + "not foud"));

            item.setIsComplete(todoItem.getIsComplete());
            item.setDescription(todoItem.getDescription());

            todoItemServices.save(item);

            return "redirect:/";

        }
        

        
    
}
