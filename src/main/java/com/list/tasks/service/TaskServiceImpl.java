package com.list.tasks.service;

import com.list.tasks.model.Task;
import com.list.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task postTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task _task = taskRepository.findById(id).get();
        _task.setTaskStatus(task.getTaskStatus());
        _task.setDescription(task.getDescription());
        _task.setTitle(task.getTitle());
        _task.setTaskPriority(task.getTaskPriority());
        _task.setLabel(task.getLabel());
        taskRepository.save(_task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

