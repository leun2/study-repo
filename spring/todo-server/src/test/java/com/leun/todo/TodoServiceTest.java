package com.leun.todo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.todo.dto.GetTodoDto;
import com.leun.todo.dto.PostTodoDto;
import com.leun.todo.dto.UpdateTodoDto;
import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import com.leun.todo.service.TodoService;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserRepository userRepository;

    private final String username = "lee";

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findAllTodos_shouldReturnTodoList() {

        Todo mockTodo =
            new Todo(
                new User(username, "1234"),
                "mock title",
                "mock description",
                false,
                LocalDate.now());

        when(todoRepository.findByUserId_UserName(username))
            .thenReturn(List.of(mockTodo));

        List<GetTodoDto> result = todoService.findAllTodos(username);

        assertNotNull(result);
        assertEquals("mock title", result.get(0).getTodoTitle());
        assertEquals("mock description", result.get(0).getTodoDescription());

        verify(todoRepository).findByUserId_UserName(username);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findTodo_shouldReturnTodo_whenUserExist() throws Exception {
        Integer mockId = 1;

        Todo mockTodo =
            new Todo(
                new User(username, "1234"),
                "mock title",
                "mock description",
                false,
                LocalDate.now());

        when(todoRepository.findById(mockId)).thenReturn(Optional.of(mockTodo));

        GetTodoDto result = todoService.findTodo(username, mockId);

        assertNotNull(result);
        assertThat(result.getTodoTitle()).isEqualTo("mock title");
        assertThat(result.getTodoDescription()).isEqualTo("mock description");
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void createTodo_shouldReturnTodoTitle_whenUserExist() {

        User mockUser = new User(username, "1234");

        PostTodoDto mockPostDto = PostTodoDto.builder()
            .todoTitle("mock title")
            .todoDescription("mock description")
            .todoDate(LocalDate.now())
            .build();

        Todo mockTodo =
            new Todo(
                mockUser,
                "mock title",
                "mock description",
                false,
                LocalDate.now());

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(mockUser));

        when(todoRepository.save(any(Todo.class))).thenReturn(mockTodo);

        String mockTitle = todoService.createTodo(username, mockPostDto);

        ArgumentCaptor<Todo> todoCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(todoCaptor.capture());

        Todo savedTodo = todoCaptor.getValue();
        assertEquals(mockPostDto.getTodoTitle(), savedTodo.getTodoTitle());
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void deleteTodo_shouldDeleteTodo_whenTodoExist() {
        Integer mockId = 1;

        User mockUser = new User(username, "1234");
        Todo mockTodo = new Todo(mockUser, "mock title", "mock description", false, LocalDate.now());

        when(todoRepository.findById(mockId)).thenReturn(Optional.of(mockTodo));

        todoService.deleteTodo(username, mockId);

        verify(todoRepository).findById(mockId);
        verify(todoRepository).deleteById(mockId);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void updateTodo_shouldUpdateTodo_whenTodoExist() {

        Integer mockId = 1;

        User mockUser = new User(username, "1234");

        Todo mockTodo =
            new Todo(mockUser, "old title", "old description", false, LocalDate.of(2024, 1, 1));
        UpdateTodoDto mockDto = UpdateTodoDto.builder()
            .todoTitle("new title")
            .todoDescription("new description")
            .todoDone(true)
            .todoDescription(LocalDate.of(2025, 1, 1).toString())
            .build();

        when(todoRepository.findById(mockId)).thenReturn(Optional.of(mockTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(mockTodo);

        // When
        todoService.updateTodo(mockId, mockDto);

        // Then
        assertEquals(mockDto.getTodoTitle(), mockTodo.getTodoTitle());
        assertEquals(mockDto.getTodoDescription(), mockTodo.getTodoDescription());
        assertEquals(mockDto.getTodoDone(), mockTodo.isTodoDone());
        assertEquals(mockDto.getTodoDate(), mockTodo.getTodoDate());

        verify(todoRepository).findById(mockId);
        verify(todoRepository).save(mockTodo);

    }
}

