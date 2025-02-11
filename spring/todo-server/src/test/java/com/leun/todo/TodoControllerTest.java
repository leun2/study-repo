package com.leun.todo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.todo.controller.TodoController;
import com.leun.todo.dto.GetTodoDto;
import com.leun.todo.dto.PostTodoDto;
import com.leun.todo.dto.UpdateTodoDto;
import com.leun.todo.service.TodoService;
import com.leun.user.entity.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TodoController.class)
public class TodoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TodoService todoService;

    private final String username = "lee";

    private final Integer todoId = 1;

    private GetTodoDto mockGetDto;

    private PostTodoDto mockPostDto;

    @BeforeEach
    void setUp() {
        mockGetDto = GetTodoDto.builder()
            .todoId(1)
            .todoTitle("mock title")
            .todoDescription("mock description")
            .todoDone(false)
            .todoDate(LocalDate.now())
            .build();

        mockPostDto = PostTodoDto.builder()
            .todoTitle("mock title")
            .todoDescription("mock description")
            .todoDate(LocalDate.now())
            .build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getTodos_shouldReturnTodoList() throws Exception {

        when(todoService.findAllTodos(username)).thenReturn(List.of(mockGetDto));

        mockMvc.perform(get("/v1/user/{user-name}/todo", "lee"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].todoId").value(1))
            .andExpect(jsonPath("$[0].todoTitle").value("mock title"))
            .andExpect(jsonPath("$[0].todoDescription").value("mock description"))
            .andExpect(jsonPath("$[0].todoDone").value("false"))
            .andExpect(jsonPath("$[0].todoDate").value(LocalDate.now().toString()));

        verify(todoService).findAllTodos(eq(username));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getTodo_shouldReturnTodo_whenTodoDoesExist() throws Exception {

        when(todoService.findTodo(username, mockGetDto.getTodoId())).thenReturn(mockGetDto);

        mockMvc.perform(get("/v1/user/{user-name}/todo/{todo-id}", username, mockGetDto.getTodoId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.todoId").value(1))
            .andExpect(jsonPath("$.todoTitle").value("mock title"))
            .andExpect(jsonPath("$.todoDescription").value("mock description"))
            .andExpect(jsonPath("$.todoDone").value("false"))
            .andExpect(jsonPath("$.todoDate").value(LocalDate.now().toString()));

        verify(todoService).findTodo(eq(username), eq(mockGetDto.getTodoId()));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void createTodo_shouldReturnTodoTitle_whenDataIsValid() throws Exception {

        when(todoService.createTodo(username, mockPostDto)).thenReturn(mockPostDto.getTodoTitle());

        String requestBody = objectMapper.writeValueAsString(mockPostDto);

        mockMvc.perform(post("/v1/user/{user-name}/todo", username)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)  // 전체 객체 전달
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(content().string(mockPostDto.getTodoTitle()));

        verify(todoService).createTodo(eq(username), eq(mockPostDto));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void deleteTodo_shouldReturnNoContent_whenSuccessful() throws Exception {

        Integer todoId = 1;

        doNothing().when(todoService).deleteTodo(username, todoId);

        mockMvc.perform(delete("/v1/user/{user-name}/todo/{todo-id}", username, todoId)
                .with(csrf()))
            .andExpect(status().isNoContent())
            .andExpect(content().string("success"));

        verify(todoService).deleteTodo(eq(username), eq(todoId));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getTodoForUpdate_shouldReturnTodo_whenTodoDoesExist() throws  Exception {

        when(todoService.findTodo(username, todoId)).thenReturn(mockGetDto);

        mockMvc.perform(get("/v1/user/{user-name}/todo/{todo-id}/update", username, todoId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.todoId").value(1))
            .andExpect(jsonPath("$.todoTitle").value("mock title"))
            .andExpect(jsonPath("$.todoDescription").value("mock description"))
            .andExpect(jsonPath("$.todoDone").value("false"))
            .andExpect(jsonPath("$.todoDate").value(LocalDate.now().toString()));

        verify(todoService).findTodo(eq(username), eq(todoId));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void updateTodo_shouldReturnTodoTitle_whenDataIsValid() throws Exception {

        UpdateTodoDto mockUpdateDto = UpdateTodoDto.builder()
            .todoTitle("mock title")
            .todoDescription("mock description")
            .todoDone(false)
            .todoDate(LocalDate.now())
            .build();

        doNothing().when(todoService).updateTodo(todoId, mockUpdateDto);

        String requestBody = objectMapper.writeValueAsString(mockUpdateDto);

        mockMvc.perform(patch("/v1/user/{user-name}/todo/{todo-id}/update", username, todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)  // 전체 객체 전달
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string("success"));

        verify(todoService).updateTodo(eq(todoId), eq(mockUpdateDto));

    }
}
