package com.list.tasks.payload;

import com.list.tasks.model.Task;
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
                            .todoStatus(TaskStatus.IN_PROGRESS)
                            .build()
            );
            taskRepository.save(
                    new Task.Builder()
                            .title("Check pipeline for the app")
                            .description("On .github/workflows/maven.yml")
                            .todoStatus(TaskStatus.TO_DO)
                            .build()
            );
            taskRepository.save(
                    new Task.Builder()
                            .title("Improve API")
                            .description("Add new features")
                            .todoStatus(TaskStatus.IN_PROGRESS)
                            .build()
            );
            System.out.println(new Timestamp(System.currentTimeMillis()) + "  DATA --- [tasks payload]        : Loaded successfully");
        }
    }
}

