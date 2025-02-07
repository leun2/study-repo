package com.leun.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import com.leun.todo.service.TodoService;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    private final String username = "lee";
    private Todo mockTodo;

    @BeforeEach
    void setUp() {
        mockTodo = new Todo(1, username, "TodoService Tset", LocalDate.now(), false);
    }

    @Test
    void findByName_ShouldReturnTodoList_WhenUserHasTodos() {
        // Given
        when(todoRepository.findByTodoName(username)).thenReturn(List.of(mockTodo));

        // When
        List<Todo> todos = todoService.findByName(username);

        // Then
        assertNotNull(todos);
        assertFalse(todos.isEmpty());
        assertEquals(username, todos.get(0).getTodoName());
        verify(todoRepository, times(1)).findByTodoName(username);
    }

    @Test
    void findById_ShouldReturnTodo_WhenTodoExists() {
        when(todoRepository.findById(1)).thenReturn(Optional.of(mockTodo));

        Todo todo = todoService.findById(1);

        assertNotNull(todo);
        assertEquals(username, todo.getTodoName());
        verify(todoRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldThrowException_WhenTodoDoesNotExist() {
        when(todoRepository.findById(0)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(
            NoSuchElementException.class,
            () -> todoService.findById(0)
        );

        assertEquals("Todo with ID 0 not found", thrown.getMessage());
        verify(todoRepository, times(1)).findById(0);
    }

    @Test
    void deleteById_ShouldDeleteTodo_WhenTodoExists() {
        // Given
        int validId = 1;
        when(todoRepository.findById(validId)).thenReturn(Optional.of(mockTodo));
        doNothing().when(todoRepository).deleteById(validId);

        // When
        todoService.deleteById(validId);

        // Then
        verify(todoRepository, times(1)).findById(validId);
        verify(todoRepository, times(1)).deleteById(validId);
    }

    @Test
    void deleteById_ShouldThrowException_WhenTodoDoesNotExist() {
        int invalidId = 999;
        when(todoRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Then
        NoSuchElementException thrown = assertThrows(
            NoSuchElementException.class,
            () -> todoService.deleteById(invalidId)
        );

        assertEquals("Todo with ID 999 not found", thrown.getMessage());
        verify(todoRepository, times(1)).findById(invalidId);
        verify(todoRepository, times(0)).deleteById(invalidId);
    }

    @Test
    void addTodo_ShouldCreateTodo_WhenValidTodoIsProvided() {

        when(todoRepository.save(any(Todo.class))).thenReturn(mockTodo);
        when(todoRepository.findByTodoName(username)).thenReturn(List.of(mockTodo));

        List<Todo> todos = todoService.addTodo(username, "TodoService Test", LocalDate.now());

        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals(username, todos.get(0).getTodoName());

        verify(todoRepository, times(1)).save(any(Todo.class));
        verify(todoRepository, times(1)).findByTodoName(username);
    }

    @Test
    void updateTodo_ShouldUpdateTodo_WhenValidTodoIsProvided() {

        // Given
        when(todoRepository.save(any(Todo.class))).thenReturn(mockTodo);

        todoService.updateTodo(mockTodo);

        // Then
        verify(todoRepository, times(1)).save(mockTodo);
    }
}