package com.list.tasks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.list.tasks.model.Label;
import com.list.tasks.model.LabelColor;
import com.list.tasks.service.LabelService;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
    @MockBean
    LabelService labelService;

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAll() throws Exception {
        Label label = new Label.Builder()
                .title("Test")
                .labelColor(LabelColor.ORANGE)
                .build();
        List<Label> labels = Arrays.asList(label);

        Mockito.when(labelService.getLabels()).thenReturn(labels);

        mockMvc.perform(get("/api/v1/labels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Test")))
                .andExpect(jsonPath("$[0].labelColor", Matchers.is("ORANGE")));
    }

    @Test
    public void testGetById() throws Exception {
        Label label = new Label.Builder()
                .title("Test")
                .labelColor(LabelColor.ORANGE)
                .build();

        label.setId((long) 1234);

        Mockito.when(labelService.getLabelById(label.getId())).thenReturn(label);

        mockMvc.perform(get("/api/v1/labels/"+label.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Test")))
                .andExpect(jsonPath("$.labelColor", Matchers.is("ORANGE")));
    }

    @Test
    public void testPost() throws Exception {
        Label label = new Label.Builder()
                .title("Test")
                .labelColor(LabelColor.ORANGE)
                .build();

        Mockito.when(labelService.postLabel(ArgumentMatchers.any())).thenReturn(label);
        String json = mapper.writeValueAsString(label);
        mockMvc.perform(post("/api/v1/labels").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", Matchers.is("Test")))
                .andExpect(jsonPath("$.labelColor", Matchers.is("ORANGE")));
    }

    @Test
    public void testPut() throws Exception {
        Label label = new Label.Builder()
                .title("Test")
                .labelColor(LabelColor.ORANGE)
                .build();
        label.setId((long) 1234);

        Mockito.when(labelService.updateLabel(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(label);
        String json = mapper.writeValueAsString(label);
        mockMvc.perform(put("/api/v1/labels/"+label.getId()).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Test")))
                .andExpect(jsonPath("$.labelColor", Matchers.is("ORANGE")));
    }

    @Test
    public void testDelete() throws Exception {
        Label label = new Label.Builder()
                .title("Test")
                .labelColor(LabelColor.ORANGE)
                .build();
        label.setId((long) 1234);

        Mockito.when(labelService.postLabel(ArgumentMatchers.any())).thenReturn(label);

        mockMvc.perform(delete("/api/v1/labels/"+label.getId()))
                .andExpect(status().isNoContent());
    }
}
