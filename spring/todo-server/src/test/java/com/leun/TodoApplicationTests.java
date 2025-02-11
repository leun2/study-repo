package com.leun;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.auth.jwt.JwtTokenService;
import com.leun.todo.dto.GetTodoDto;
import com.leun.todo.dto.PostTodoDto;
import com.leun.todo.dto.UpdateTodoDto;
import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import com.leun.todo.service.TodoService;
import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import com.leun.user.service.UserService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String username = "lee";

    private final String password = "1234";

    private final User mockUser = new User(username, password);

    private final Todo mockTodo =
        new Todo(mockUser, "mock todo title", "mock todo description", false, LocalDate.now());

    private String globalToken;

    @BeforeAll
    void init() {
        userRepository.save(mockUser);
        todoRepository.save(mockTodo);

        Authentication authenticationToken =
            new UsernamePasswordAuthenticationToken(username, password);

        globalToken = tokenService.generateToken(authenticationToken);
    }

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
        todoRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Transactional
    void createUserTest() throws Exception {

        UserDto mockUser = UserDto.builder()
            .userName("lee")
            .userPassword("1234")
            .build();

        String result = objectMapper.writeValueAsString(mockUser);

        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
                .with(csrf())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isCreated())
            .andExpect(content().string(mockUser.getUserName()));
    }

    @Test
    @Transactional
    void getUserByNameTest() throws Exception {

        mockMvc.perform(get("/v1/user/{user-name}", username)
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value(username))
            .andExpect(jsonPath("$.userPassword").value(password));
    }

    @Test
    @Transactional
    void getUserByIdTest() throws Exception {

        mockMvc.perform(get("/v1/user/search/{user-id}", mockUser.getUserId())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value(username))
            .andExpect(jsonPath("$.userPassword").value(password));
    }

    @Test
    @Transactional
    void createTodoTest() throws Exception {

        PostTodoDto mockDto = PostTodoDto.builder()
            .todoTitle("mock todo title")
            .todoDescription("mock todo description")
            .todoDate(LocalDate.now())
            .build();

        String result = objectMapper.writeValueAsString(mockDto);

        mockMvc.perform(post("/v1/user/{user-name}/todo", username)
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
                .with(csrf())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isCreated())
            .andExpect(content().string(mockDto.getTodoTitle()));
    }

    @Test
    @Transactional
    void getTodosTest() throws Exception {

        List<GetTodoDto> mockTodos = todoService.findAllTodos(username);

        mockMvc.perform(get("/v1/user/{user-name}/todo", username)
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].todoId").value(mockTodo.getTodoId().toString()))
            .andExpect(jsonPath("$[0].todoTitle").value("mock todo title"))
            .andExpect(jsonPath("$[0].todoDescription").value("mock todo description"))
            .andExpect(jsonPath("$[0].todoDone").value("false"))
            .andExpect(jsonPath("$[0].todoDate").value(LocalDate.now().toString()));
    }

    @Test
    @Transactional
    void getTodoTest() throws Exception {

        GetTodoDto todoDto = todoService.findTodo(username, mockTodo.getTodoId());
        mockMvc.perform(get("/v1/user/{user-name}/todo/{todo-id}", username, mockTodo.getTodoId())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.todoId").value(todoDto.getTodoId()))
            .andExpect(jsonPath("$.todoTitle").value(todoDto.getTodoTitle()))
            .andExpect(jsonPath("$.todoDescription").value(todoDto.getTodoDescription()))
            .andExpect(jsonPath("$.todoDone").value(todoDto.getTodoDone()))
            .andExpect(jsonPath("$.todoDate").value(LocalDate.now().toString()));
    }

    @Test
    @Transactional
    void updateTodoTest() throws Exception {

        UpdateTodoDto mockDto = UpdateTodoDto.builder()
            .todoTitle("mock todo title")
            .todoDescription("mock todo description")
            .todoDone(true)
            .todoDate(LocalDate.now())
            .build();

        String result = objectMapper.writeValueAsString(mockDto);

        mockMvc.perform(patch("/v1/user/{user-name}/todo/{todo-id}/update", username, mockTodo.getTodoId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
                .with(csrf())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isOk())
            .andExpect(content().string("update-success"));
    }

    @Test
    @Transactional
    void deleteTodoTest() throws Exception {

        mockMvc.perform(delete("/v1/user/{user-name}/todo/{todo-id}", username, mockTodo.getTodoId())
                .with(csrf())
                .header("Authorization", "Bearer " + globalToken))
            .andExpect(status().isNoContent())
            .andExpect(content().string("delete-success"));
    }
}
