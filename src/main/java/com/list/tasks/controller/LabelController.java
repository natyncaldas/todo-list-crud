package com.list.tasks.controller;

import com.list.tasks.model.Label;
import com.list.tasks.service.LabelService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labels")
public class LabelController {
    LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping
    public ResponseEntity<List<Label>> getAllLabels() {
        List<Label> tasks = labelService.getLabels();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Label> getLabel(@PathVariable("id") Long id) {
        return new ResponseEntity<>(labelService.getLabelById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Label> postLabel(@RequestBody Label label) {
        Label _label = labelService.postLabel(label);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("label", "/api/v1/labels/" + _label.getId().toString());
        return new ResponseEntity<>(_label, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Label> updateLabel(@PathVariable("id") Long id, @RequestBody Label label) {
        labelService.updateLabel(id, label);
        return new ResponseEntity<>(labelService.getLabelById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Label> deleteLabel(@PathVariable("id") Long id) {
        labelService.deleteLabel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
