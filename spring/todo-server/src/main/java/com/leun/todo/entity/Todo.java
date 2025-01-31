package com.leun.todo.entity;

import com.leun.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer todoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "todo_title")
    private String todoTitle;

    @Column(name = "todo_description")
    private String todoDescription;

    @Column(name = "todo_done")
    private boolean todoDone;

    @Column(name = "todo_date")
    private LocalDate todoDate;

    public Todo(User userId, String todoTitle, String todoDescription, boolean todoDone,
        LocalDate todoDate) {
        this.userId = userId;
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoDone = todoDone;
        this.todoDate = todoDate;
    }
}
