package com.leun;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import com.leun.todo.service.TodoService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    private Todo mockTodo;

    @BeforeEach
    void setUp() {
        mockTodo =
            new Todo(
                "lee",
                "Todo Integration Test",
                LocalDate.now(),
                false);
        todoRepository.save(mockTodo);
    }

    @AfterEach
    void tearDown() {
//        todoRepository.deleteAll();
    }

    @Test
    @Transactional
    @WithMockUser(username = "lee", roles = {"USER", "ROOT"})
    void getHomeTest() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("home"));
    }

    @Test
    @Transactional
    @DisplayName("get todos test")
    @WithMockUser(username = "lee", roles = {"USER", "ROOT"})
    void getTodosTest() throws Exception {

        mockMvc.perform(get("/todo"))
            .andExpect(status().isOk())
            .andExpect(view().name("todo"))
            .andExpect(model().attributeExists("todos"));

        List<Todo> todos = todoService.findByName("lee");
        assertThat(todos).hasSize(1);
        assertThat(todos.get(0).getTodoDescription())
            .isEqualTo("Todo Integration Test");
    }

    @Test
    @Transactional
    @WithMockUser(username = "lee", roles = {"USER", "ROOT"})
    void addTodoTest() throws Exception {

        mockMvc.perform(post("/add-todo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("todoDescription", "Todo Integration Test")
                .param("todoDate", LocalDate.now().toString())
                .param("todoId","0")
                .param("todoDone","false")
                .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/todo"));

        Optional<Todo> savedTodo = todoRepository.findByTodoName("lee").stream()
            .filter(todo -> todo.getTodoDescription().equals("Todo Integration Test"))
            .findFirst();

        assertTrue(savedTodo.isPresent(), "Todo should be saved");
    }

    @Test
    @Transactional
    @WithMockUser(username = "lee", roles = {"USER", "ROOT"})
    void deleteTodoTest() throws Exception {

        mockMvc.perform(get("/delete-todo")
                .param("id", mockTodo.getTodoId().toString()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/todo"));

        Optional<Todo> deletedTodo = todoRepository.findById(mockTodo.getTodoId());
        assertTrue(deletedTodo.isEmpty(), "Todo should be deleted");
    }

    @Test
    @Transactional
    @WithMockUser(username = "lee", roles = {"USER", "ROOT"})
    void testUpdateTodo() throws Exception {
        mockMvc.perform(post("/update-todo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("todoId", mockTodo.getTodoId().toString())
                .param("todoDescription", "Updated Description")
                .param("todoDate", LocalDate.now().toString()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/todo"));

        Todo updatedTodo = todoRepository.findById(mockTodo.getTodoId()).orElseThrow();

        assertEquals(
            "Updated Description",
            updatedTodo.getTodoDescription(),
            "Todo should be updated");
    }
}
