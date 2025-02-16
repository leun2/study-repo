package com.leun.todo.controller;

import com.leun.todo.dto.GetTodoDto;
import com.leun.todo.dto.PostTodoDto;
import com.leun.todo.dto.UpdateTodoDto;
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
    public ResponseEntity<List<GetTodoDto>> getTodos(
        @PathVariable("user-name") String userName) {
        List<GetTodoDto> todos = todoService.findAllTodos(userName);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/todo/{todo-id}")
    public ResponseEntity<GetTodoDto> getTodo(
        @PathVariable("user-name") String userName, @PathVariable("todo-id") Integer todoId) {
        GetTodoDto getTodoDto = todoService.findTodo(userName, todoId);

        return new ResponseEntity<>(getTodoDto, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<String> createTodo(@PathVariable("user-name") String userName,
        @RequestBody PostTodoDto todoDto) {

        String todoTitle = todoService.createTodo(userName, todoDto);

        return new ResponseEntity<>(todoTitle, HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{todo-id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("user-name") String userName,
        @PathVariable("todo-id") Integer todoId) {
        todoService.deleteTodo(userName, todoId);

        return new ResponseEntity<>("delete-success", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/todo/{todo-id}/update")
    public ResponseEntity<GetTodoDto> getTodoForUpdate(@PathVariable("user-name") String userName,
        @PathVariable("todo-id") Integer todoId) {
        GetTodoDto getTodoDto = todoService.findTodo(userName, todoId);

        return new ResponseEntity<>(getTodoDto, HttpStatus.OK);
    }

    @PatchMapping("/todo/{todo-id}/update")
    public ResponseEntity<String> updateTodo(@PathVariable("user-name") String userName,
    @PathVariable("todo-id") Integer todoId, @RequestBody UpdateTodoDto todoDto) {
        todoService.updateTodo(todoId, todoDto);

        return new ResponseEntity<>("update-success", HttpStatus.OK);
    }
}
