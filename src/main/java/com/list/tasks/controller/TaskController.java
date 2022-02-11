package com.list.tasks.controller;

import com.list.tasks.model.Task;
import com.list.tasks.service.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        Task _task = taskService.postTask(task);
        return new ResponseEntity<>(_task, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Task _task = taskService.updateTask(id, task);
        return new ResponseEntity<>(_task, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
