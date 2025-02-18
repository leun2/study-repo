package com.leun.todo.service;

import com.leun.todo.dto.GetTodoDto;
import com.leun.todo.dto.PostTodoDto;
import com.leun.todo.dto.UpdateTodoDto;
import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<GetTodoDto> findAllTodos(String userName) {
        List<Todo> todos = todoRepository.findByUserId_UserName(userName);

        return todos.stream()
            .map(todo -> GetTodoDto.builder()
                .todoId(todo.getTodoId())
                .todoTitle(todo.getTodoTitle())
                .todoDescription(todo.getTodoDescription())
                .todoDone(todo.isTodoDone())
                .todoDate(todo.getTodoDate())
                .build())
            .collect(Collectors.toList());
    }

    public String createTodo(String userName, PostTodoDto todoDto) {
        User user = userRepository.findByUserName(userName).orElseThrow(
            () -> new NoSuchElementException("User Does Not Exist")
        );
        Todo todo = new Todo(user, todoDto.getTodoTitle(), todoDto.getTodoDescription(), false,
            todoDto.getTodoDate());
        return todoRepository.save(todo).getTodoTitle();
    }

    public GetTodoDto findTodo(String userName, Integer todoId) {
        Todo todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + todoId));

        if (userName.equals(todo.getUserId().getUserName())) {
            return GetTodoDto.builder()
                .todoId(todo.getTodoId())
                .todoTitle(todo.getTodoTitle())
                .todoDescription(todo.getTodoDescription())
                .todoDone(todo.isTodoDone())
                .todoDate(todo.getTodoDate())
                .build();
        } else {
            return GetTodoDto.builder().build();
        }
    }

    public void deleteTodo(String userName, Integer todoId) {

        Todo todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + todoId));

        if (userName.equals(todo.getUserId().getUserName())) {
            todoRepository.deleteById(todoId);
        }
    }

    public void updateTodo(Integer todoId, UpdateTodoDto todoDto) {

        Todo todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + todoId));

        todo.setTodoTitle(todoDto.getTodoTitle());
        todo.setTodoDescription(todoDto.getTodoDescription());
        todo.setTodoDone(todoDto.getTodoDone());
        todo.setTodoDate(todoDto.getTodoDate());

        todoRepository.save(todo);
    }
}
