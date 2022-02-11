package com.list.tasks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.list.tasks.model.*;
import com.list.tasks.service.TaskService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAll() throws Exception {
        Task task = new Task.Builder()
                .title("Code API")
                .description("Using spring boot")
                .taskStatus(TaskStatus.TO_DO)
                .build();
        List<Task> tasks = Arrays.asList(task);

        Mockito.when(taskService.getTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Code API")))
                .andExpect(jsonPath("$[0].description", Matchers.is("Using spring boot")))
                .andExpect(jsonPath("$[0].taskStatus", Matchers.is("TO_DO")));
    }

    @Test
    public void testGetById() throws Exception {
        Task task = new Task.Builder()
                .title("Code API")
                .description("Using spring boot")
                .taskStatus(TaskStatus.TO_DO)
                .build();

        task.setId((long) 1234);

        Mockito.when(taskService.getTaskById(task.getId())).thenReturn(task);

        mockMvc.perform(get("/api/v1/tasks/"+task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Code API")))
                .andExpect(jsonPath("$.description", Matchers.is("Using spring boot")))
                .andExpect(jsonPath("$.taskStatus", Matchers.is("TO_DO")));
    }

    @Test
    public void testPost() throws Exception {
        Task task = new Task.Builder()
                .title("Code API")
                .description("Using spring boot")
                .taskStatus(TaskStatus.TO_DO)
                .build();

        Mockito.when(taskService.postTask(ArgumentMatchers.any())).thenReturn(task);
        String json = mapper.writeValueAsString(task);
        mockMvc.perform(post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", Matchers.is("Code API")))
                .andExpect(jsonPath("$.description", Matchers.is("Using spring boot")))
                .andExpect(jsonPath("$.taskStatus", Matchers.is("TO_DO")));
    }

    @Test
    public void testPut() throws Exception {
        Task task = new Task.Builder()
                .title("Code API")
                .description("Using spring boot")
                .taskStatus(TaskStatus.TO_DO)
                .build();
        task.setId((long) 1234);

        Mockito.when(taskService.updateTask(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(task);
        String json = mapper.writeValueAsString(task);
        mockMvc.perform(put("/api/v1/tasks/"+task.getId()).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Code API")))
                .andExpect(jsonPath("$.description", Matchers.is("Using spring boot")))
                .andExpect(jsonPath("$.taskStatus", Matchers.is("TO_DO")));
    }

    @Test
    public void testDelete() throws Exception {
        Task task = new Task.Builder()
                .title("Code API")
                .description("Using spring boot")
                .taskStatus(TaskStatus.TO_DO)
                .build();
        task.setId((long) 1234);

        Mockito.when(taskService.postTask(ArgumentMatchers.any())).thenReturn(task);

        mockMvc.perform(delete("/api/v1/tasks/"+task.getId()))
                .andExpect(status().isNoContent());
    }
}
