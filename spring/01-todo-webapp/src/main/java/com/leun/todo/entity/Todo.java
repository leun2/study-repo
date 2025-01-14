package com.leun.todo.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo {

    public void setTodo_id(Integer todo_id) {
        this.todo_id = todo_id;
    }

    public void setTodo_username(String todo_username) {
        this.todo_username = todo_username;
    }

    public void setTodo_description(String todo_description) {
        this.todo_description = todo_description;
    }

    public void setTodo_datetime(LocalDate todo_datetime) {
        this.todo_datetime = todo_datetime;
    }

    public void setTodo_done(boolean todo_done) {
        this.todo_done = todo_done;
    }

    public Todo(Integer todo_id, String todo_username, String todo_description,
        LocalDate todo_datetime, boolean todo_done) {
        this.todo_id = todo_id;
        this.todo_username = todo_username;
        this.todo_description = todo_description;
        this.todo_datetime = todo_datetime;
        this.todo_done = todo_done;
    }

    private Integer todo_id;
    private String todo_username;
    @Size(min=10, message = "Enter at least 10 char")
    private String todo_description;
    private LocalDate todo_datetime;
    private boolean todo_done;

    public Integer getTodo_id() {
        return todo_id;
    }

    public String getTodo_username() {
        return todo_username;
    }

    public String getTodo_description() {
        return todo_description;
    }

    public LocalDate getTodo_datetime() {
        return todo_datetime;
    }

    public boolean isTodo_done() {
        return todo_done;
    }

    @Override
    public String toString() {
        return "Todo{" +
            "todo_id=" + todo_id +
            ", todo_username='" + todo_username + '\'' +
            ", todo_description='" + todo_description + '\'' +
            ", todo_datetime=" + todo_datetime +
            ", todo_done=" + todo_done +
            '}';
    }
}
