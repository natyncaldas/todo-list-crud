package com.list.tasks.service;

import com.list.tasks.model.Task;
import com.list.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository todoRepository;

    public TaskServiceImpl(TaskRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        todoRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public Task postTask(Task task) {
        return todoRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task _task = todoRepository.findById(id).get();
        _task.setTodoStatus(task.getTodoStatus());
        _task.setDescription(task.getDescription());
        _task.setTitle(task.getTitle());
        todoRepository.save(_task);
    }

    @Override
    public void deleteTask(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}

