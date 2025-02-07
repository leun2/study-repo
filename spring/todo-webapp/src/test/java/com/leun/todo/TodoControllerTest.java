package com.leun.todo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.leun.todo.controller.TodoController;
import com.leun.todo.entity.Todo;
import com.leun.todo.service.TodoService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    private final String username = "lee";

    private Todo mockTodo;

    @BeforeEach
    void setUp() {
        mockTodo = new Todo(1, username, "TodoController Test", LocalDate.now(), false);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void getTodos_ShouldReturnTodoWithModelAttribute() throws Exception {

        List<Todo> mockTodos = List.of(mockTodo);

        when(todoService.getUserName()).thenReturn(username);
        when(todoService.findByName(username)).thenReturn(mockTodos);

        mockMvc.perform(get("/todo"))
            .andExpect(status().isOk())
            .andExpect(view().name("todo"))
            .andExpect(model().attribute("todos", mockTodos));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void postTodo_ShouldReturnAddTodoWithModelAttribute() throws Exception {

        when(todoService.getUserName()).thenReturn(username);

        mockMvc.perform(get("/add-todo"))
            .andExpect(status().isOk())
            .andExpect(view().name("add-todo"))
            .andExpect(
                model().attribute("todo", new Todo(0, username, "", LocalDate.now(), false)));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void postTodo_ShouldRedirectToTodo_WhenValidDataProvided() throws Exception {

        String description = "TodoController Test";

        // Given (Mock 설정)
        when(todoService.getUserName()).thenReturn(username);
        when(todoService.addTodo(eq(username), eq(description), any(LocalDate.class)))
            .thenReturn(List.of(mockTodo));

        // When & Then (MockMvc 요청 테스트)
        mockMvc.perform(post("/add-todo")
                .param("todoDescription", description)
                .param("todoDate", LocalDate.now().toString()) // LocalDate 변환
                .param("todoId", "1") // hidden 필드 포함
                .param("todoDone", "false") // hidden 필드 포함
                .with(csrf())) // CSRF 보호 해제
            .andExpect(status().is3xxRedirection()) // 302 리다이렉트 확인
            .andExpect(redirectedUrl("/todo")); // 리다이렉트 URL 확인

        verify(todoService).addTodo(eq(username), eq(description), any(LocalDate.class));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void postTodo_ShouldReturnAddTodo_WhenInvalidDataProvided() throws Exception {
        mockMvc.perform(post("/add-todo")
                .param("todoDescription", "short")
                .param("todoDate", "invalid-date")
                .param("todoId", "0") // hidden 필드 포함
                .param("todoDone", "false") // hidden 필드 포함
                .with(csrf()))
            .andExpect(status().isOk()) // 200 OK (리다이렉트가 아니라 폼으로 포워드됨)
            .andExpect(view().name("add-todo"));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void deleteTodo_ShouldRedirectToTodo() throws Exception {

        mockMvc.perform(get("/delete-todo").param("id", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/todo"));

        verify(todoService).deleteById(1);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void updateTodo_ShouldReturnUpdateTodoWithModelAttribute() throws Exception {

        when(todoService.findById(1)).thenReturn(mockTodo);

        mockMvc.perform(get("/update-todo")
                .param("id", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("update-todo"))
            .andExpect(model().attributeExists("todo"))
            .andExpect(model().attribute("todo", mockTodo));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER", "ADMIN"})
    void updateTodo_ShouldRedirectToTodo_WhenValidDataProvided() throws Exception {

        when(todoService.getUserName()).thenReturn(username);

        mockMvc.perform(post("/update-todo")
                .param("id", "1")
                .param("todoDescription", "updateTodo Test")
                .param("todoDate", LocalDate.now().toString()) // LocalDate 변환
                .param("todoId", "1") // hidden 필드 포함
                .param("todoDone", "false") // hidden 필드 포함
                .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/todo"));
    }
}