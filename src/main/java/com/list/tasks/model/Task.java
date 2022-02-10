package com.list.tasks.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String title;
    @Column
    String description;
    @Column
    TaskStatus todoStatus;


    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    @UpdateTimestamp
    Timestamp lastModified;

    public Task(Long id, String title, String description, TaskStatus todoStatus, Timestamp dateCreated, Timestamp lastModified) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.todoStatus = todoStatus;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
    }

    public Task() {
    }

    public Task(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.todoStatus = builder.todoStatus;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getTodoStatus() {
        return todoStatus;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTodoStatus(TaskStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                title.equals(task.title) &&
                Objects.equals(description, task.description) &&
                todoStatus == task.todoStatus &&
                dateCreated.equals(task.dateCreated) &&
                lastModified.equals(task.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, todoStatus, dateCreated, lastModified);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", todoStatus=" + todoStatus +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                '}';
    }
    public static class Builder
    {
        private Long id;
        private String title;
        private String description;
        private TaskStatus todoStatus;
        private Timestamp dateCreated;
        private Timestamp lastModified;

        public Builder() {
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder todoStatus(TaskStatus todoStatus) {
            this.todoStatus = todoStatus;
            return this;
        }
        public Task build() {
            Task task =  new Task(this);
            return task;
        }
    }
}
