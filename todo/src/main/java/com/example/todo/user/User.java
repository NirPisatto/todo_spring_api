package com.example.todo.user;

import com.example.todo.task.Task;
import jakarta.persistence.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"users\"", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")}
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String fistName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private LocalDate create_date;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    public User() {

    }

    public User(String first_name, String last_name, String email, String password) {
        this.fistName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.create_date = LocalDate.now();
    }

    @Transient
    public String getFullName() {
        return this.fistName + " " + this.lastName;
    }

    public String getFirstName() {
        return fistName;
    }

    public void setFirstName(String first_name) {
        this.fistName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateDate() {
        return create_date;
    }

    public void setCreateDate(LocalDate create_date) {
        this.create_date = create_date;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName() + "{");

        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                result.append(field.getName()).append("='").append(field.get(this)).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Remove the trailing comma and space if there are fields
        if (fields.length > 0) {
            result.setLength(result.length() - 2);
        }

        result.append("}");

        return result.toString();
    }
}
