package com.leun.todo.controller;

import com.leun.todo.dto.TodoGetDto;
import com.leun.todo.dto.TodoPostDto;
import com.leun.todo.dto.TodoUpdateDto;
import com.leun.todo.entity.Todo;
import com.leun.todo.service.TodoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user/{user-name}")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public ResponseEntity<List<TodoGetDto>> getAllTodos(
        @PathVariable("user-name") String userName) {
        List<TodoGetDto> todos = todoService.findAllTodos(userName);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/todo/{todo-id}")
    public ResponseEntity<TodoGetDto> getTodo(
        @PathVariable("user-name") String userName, @PathVariable("todo-id") Integer todoId) {
        TodoGetDto todoGetDto = todoService.findTodo(userName, todoId);

        return new ResponseEntity<>(todoGetDto, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<String> postTodo(@PathVariable("user-name") String userName,
        @RequestBody TodoPostDto todoDto) {

        String todoTitle = todoService.createTodo(userName, todoDto);

        return new ResponseEntity<>(todoTitle, HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{todo-id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("user-name") String userName,
        @PathVariable("todo-id") Integer todoId) {
        todoService.deleteTodo(userName, todoId);

        return new ResponseEntity<>(userName, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/todo/{todo-id}/update")
    public ResponseEntity<TodoGetDto> getUpdateDto(@PathVariable("user-name") String userName,
        @PathVariable("todo-id") Integer todoId) {
        TodoGetDto todoGetDto = todoService.findTodo(userName, todoId);

        return new ResponseEntity<>(todoGetDto, HttpStatus.OK);
    }

    @PatchMapping("/todo/{todo-id}/update")
    public ResponseEntity<String> updateTodo(@PathVariable("user-name") String userName,
    @PathVariable("todo-id") Integer todoId, @RequestBody TodoUpdateDto todoDto) {
        String todoTitle = todoService.updateTodo(todoId, todoDto);

        return new ResponseEntity<>(todoTitle, HttpStatus.OK);
    }
}
