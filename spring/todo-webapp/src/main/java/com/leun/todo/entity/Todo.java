package com.leun.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer todoId;

    @Column(name = "todo_name")
    private String todoName;

    @Column(name = "todo_description")
    @Size(min=10, message = "Enter at least 10 char")
    private String todoDescription;

    @Column(name = "todo_date")
    private LocalDate todoDate;

    @Column(name = "todo_done")
    private boolean todoDone;

    public Todo(String todoName, String todoDescription, LocalDate todoDate, boolean todoDone) {
        this.todoName = todoName;
        this.todoDescription = todoDescription;
        this.todoDate = todoDate;
        this.todoDone = todoDone;
    }
}
