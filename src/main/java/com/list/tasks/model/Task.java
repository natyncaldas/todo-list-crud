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
    TaskStatus taskStatus;
    @Column
    TaskPriority taskPriority;


    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    @UpdateTimestamp
    Timestamp lastModified;

    public Task(Long id, String title, String description, TaskStatus taskStatus, TaskPriority taskPriority, Timestamp dateCreated, Timestamp lastModified) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
    }

    public Task() {
    }

    public Task(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.taskStatus = builder.taskStatus;
        this.taskPriority = builder.taskPriority;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
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

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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
                taskStatus == task.taskStatus &&
                dateCreated.equals(task.dateCreated) &&
                lastModified.equals(task.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, taskStatus, dateCreated, lastModified);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                '}';
    }
    public static class Builder
    {
        private Long id;
        private String title;
        private String description;
        private TaskStatus taskStatus;
        private TaskPriority taskPriority;

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
        public Builder taskStatus(TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }
        public Builder taskPriority(TaskPriority taskPriority) {
            this.taskPriority = taskPriority;
            return this;
        }
        public Task build() {
            Task task =  new Task(this);
            return task;
        }
    }
}
