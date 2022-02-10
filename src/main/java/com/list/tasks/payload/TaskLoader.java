package com.list.tasks.payload;

import com.list.tasks.model.Task;
import com.list.tasks.model.TaskPriority;
import com.list.tasks.model.TaskStatus;
import com.list.tasks.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TaskLoader implements CommandLineRunner {
    public final TaskRepository taskRepository;

    public TaskLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTasks();
    }

    private void loadTasks() {
        if (taskRepository.count() == 0) {
            taskRepository.save(
                    new Task.Builder()
                            .title("Create unit tests for this app")
                            .description("On route api/v1/tasks")
                            .taskStatus(TaskStatus.IN_PROGRESS)
                            .taskPriority(TaskPriority.MEDIUM)
                            .build()
            );
            taskRepository.save(
                    new Task.Builder()
                            .title("Check pipeline for the app")
                            .description("On .github/workflows/maven.yml")
                            .taskStatus(TaskStatus.TO_DO)
                            .taskPriority(TaskPriority.HIGH)
                            .build()
            );
            taskRepository.save(
                    new Task.Builder()
                            .title("Improve API")
                            .description("Add new features")
                            .taskStatus(TaskStatus.IN_PROGRESS)
                            .taskPriority(TaskPriority.LOW)
                            .build()
            );
            System.out.println(new Timestamp(System.currentTimeMillis()) + "  DATA --- [tasks payload]        : Loaded successfully");
        }
    }
}

