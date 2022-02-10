package com.list.tasks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Label {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String title;
    @Column
    LabelColor labelColor;

    public Label(Long id, String title, LabelColor labelColor) {
        this.id = id;
        this.title = title;
        this.labelColor = labelColor;
    }

    public Label() {
    }

    public Label(Label.Builder builder) {
        this.title = builder.title;
        this.labelColor = builder.labelColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LabelColor getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(LabelColor labelColor) {
        this.labelColor = labelColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id.equals(label.id) &&
                title.equals(label.title) &&
                labelColor == label.labelColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, labelColor);
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", labelColor=" + labelColor +
                '}';
    }

    public static class Builder
    {
        private Long id;
        private String title;
        private LabelColor labelColor;

        public Builder() {
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder labelColor(LabelColor labelColor) {
            this.labelColor = labelColor;
            return this;
        }
        public Label build() {
            return new Label(this);
        }
    }
}

