package com.list.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.list.todo.model.Todo;
import com.list.todo.model.TodoStatus;
import com.list.todo.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @MockBean
    TodoService todoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        Todo todo = new Todo.Builder()
                .title("Go to market")
                .description("Buy eggs from market")
                .todoStatus(TodoStatus.NOT_STARTED)
                .build();
        List<Todo> todos = Arrays.asList(todo);

        Mockito.when(todoService.getTodos()).thenReturn(todos);

        mockMvc.perform(get("/api/v1/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Go to market")));
    }
}
