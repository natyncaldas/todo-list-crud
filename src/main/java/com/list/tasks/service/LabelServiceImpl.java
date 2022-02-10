package com.list.tasks.service;

import com.list.tasks.model.Label;
import com.list.tasks.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public List<Label> getLabels() {
        List<Label> tasks = new ArrayList<>();
        labelRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Label getLabelById(Long id) {
        return labelRepository.findById(id).get();
    }

    @Override
    public Label postLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void updateLabel(Long id, Label label) {
        Label _label = labelRepository.findById(id).get();
        _label.setTitle(label.getTitle());
        _label.setLabelColor(label.getLabelColor());
        labelRepository.save(_label);
    }

    @Override
    public void deleteLabel(Long labelId) {
        labelRepository.deleteById(labelId);
    }
}

