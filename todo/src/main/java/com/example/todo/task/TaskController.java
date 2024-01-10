package com.example.todo.task;


import com.example.todo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "0") Long userId,
            @RequestParam(required = false) Task.Status status) {

        return taskService.getTasks(PageRequest.of(page, size), userId, status);
    }

    @PostMapping
    public void registerNewTask(@RequestBody Task task) {

        taskService.addNewTask(task);
    }

    @PutMapping
    public void updateTask(@RequestBody Task task) {
//        taskService.updateTask(task);
    }

//    @AutoConfigureRestDocs(outputDir = "target/snippets")
    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

}
