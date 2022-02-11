package com.list.tasks.service;

import com.list.tasks.model.Label;

import java.util.List;

public interface LabelService {
    List<Label> getLabels();

    Label getLabelById(Long id);

    Label postLabel(Label label);

    Label updateLabel(Long id, Label label);

    void deleteLabel(Long labelId);
}
