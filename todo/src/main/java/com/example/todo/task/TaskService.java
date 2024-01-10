package com.example.todo.task;

import com.example.todo.user.User;
import com.example.todo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("This is a task service class")
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Page<Task> getTasks(PageRequest pageRequest, Long user_id, Task.Status status) {
        return this.taskRepository.findAllByUserIdAndStatusOrderByIdAsc(user_id,Task.Status.NEW,pageRequest);
    }

    public void addNewTask(Task task) {
        User user =  this.userRepository.findUserById(1L);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        task.setUser(user);
        this.taskRepository.save(task);
    }

    public void updateTask(Task task) {
        this.taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        boolean exists = this.taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException("Task with id " + taskId + " does not exists");
        }
        this.taskRepository.deleteById(taskId);
    }
}
