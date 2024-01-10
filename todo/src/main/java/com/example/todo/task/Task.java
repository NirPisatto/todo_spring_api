package com.example.todo.task;


import com.example.todo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

@Entity
@Table(name = "tasks")
public class Task {


    public enum Priority {
        LOW, MID, HI
    }

    public enum Status {
        NEW, PENDING, DONE
    }
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(30) DEFAULT 'NEW'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority", columnDefinition = "VARCHAR(30) DEFAULT 'NEW'")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDate create_date;

    public Task() {

    }

    public Task(String title, String description, String status, String priority, User user) {
        this.title = title;
        this.description = description;
        this.status = Status.valueOf(status.toUpperCase());
        this.priority = Priority.valueOf(priority.toUpperCase());
        this.create_date = LocalDate.now();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }

    public String getPriority() {
        return priority.toString();
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority.toUpperCase());
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
