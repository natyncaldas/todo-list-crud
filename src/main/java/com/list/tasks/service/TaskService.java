package com.list.tasks.service;

import com.list.tasks.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    Task getTaskById(Long id);

    Task postTask(Task task);

    void updateTask(Long id, Task task);

    void deleteTask(Long todoId);
}
