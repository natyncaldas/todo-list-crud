package com.list.todo.bootstrap;

import com.list.todo.model.Todo;
import com.list.todo.model.TodoStatus;
import com.list.todo.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoLoader implements CommandLineRunner {
    public final TodoRepository todoRepository;

    public TodoLoader(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTodos();
    }

    private void loadTodos() {
        if (todoRepository.count() == 0) {
            todoRepository.save(
                    new Todo.Builder()
                            .title("Go to market")
                            .description("Buy eggs from market")
                            .todoStatus(TodoStatus.NOT_STARTED)
                            .build()
            );
            todoRepository.save(
                    new Todo.Builder()
                            .title("Go to school")
                            .description("Complete assignments")
                            .todoStatus(TodoStatus.NOT_STARTED)
                            .build()
            );
            System.out.println("Sample Todos Loaded");
        }
    }
}

