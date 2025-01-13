package com.leun.todo.controller;

import com.leun.todo.entity.Todo;
import com.leun.todo.service.TodoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
@RequestMapping("todo")
public class TodoContorller {

    private TodoService todoService;

    public TodoContorller(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String todo(ModelMap modelMap) {
        List<Todo> todos = todoService.findByUser("lee");

        modelMap.put("todos",todos);

        return "todo";
    }
}
