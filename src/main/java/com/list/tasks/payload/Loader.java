package com.list.tasks.payload;

import com.list.tasks.model.*;
import com.list.tasks.repository.LabelRepository;
import com.list.tasks.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Loader implements CommandLineRunner {
    public final TaskRepository taskRepository;
    public final LabelRepository labelRepository;

    public Loader(TaskRepository taskRepository, LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadLabels();
        loadTasks();
    }

    private void loadLabels() {
        if (labelRepository.count() == 0) {
            labelRepository.save(
                    new Label.Builder()
                            .title("Doc")
                            .labelColor(LabelColor.GREEN)
                            .build()
            );
            labelRepository.save(
                    new Label.Builder()
                            .title("Bug")
                            .labelColor(LabelColor.RED)
                            .build()
            );
            labelRepository.save(
                    new Label.Builder()
                            .title("Enhancement")
                            .labelColor(LabelColor.PURPLE)
                            .build()
            );
            System.out.println(new Timestamp(System.currentTimeMillis()) + "  DATA --- [labels payload]        : Loaded successfully");
        }
    }

    private void loadTasks() {
        if (taskRepository.count() == 0) {
            taskRepository.save(
                    new Task.Builder()
                            .title("Create unit tests for this app")
                            .description("On route api/v1/tasks")
                            .taskStatus(TaskStatus.IN_PROGRESS)
                            .taskPriority(TaskPriority.MEDIUM)
                            .label(new Label.Builder().title("Test").labelColor(LabelColor.ORANGE).build())
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

