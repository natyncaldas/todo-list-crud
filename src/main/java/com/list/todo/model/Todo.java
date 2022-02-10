package com.list.todo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String title;
    @Column
    String description;
    @Column
    TodoStatus todoStatus;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    @UpdateTimestamp
    Timestamp lastModified;

    public Todo(Long id, String title, String description, TodoStatus todoStatus, Timestamp dateCreated, Timestamp lastModified) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.todoStatus = todoStatus;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
    }

    public Todo() {
    }

    public Todo(Builder builder) {
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

    public TodoStatus getTodoStatus() {
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

    public void setTodoStatus(TodoStatus todoStatus) {
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
        Todo todo = (Todo) o;
        return id.equals(todo.id) &&
                title.equals(todo.title) &&
                Objects.equals(description, todo.description) &&
                todoStatus == todo.todoStatus &&
                dateCreated.equals(todo.dateCreated) &&
                lastModified.equals(todo.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, todoStatus, dateCreated, lastModified);
    }

    @Override
    public String toString() {
        return "Todo{" +
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
        private TodoStatus todoStatus;
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
        public Builder todoStatus(TodoStatus todoStatus) {
            this.todoStatus = todoStatus;
            return this;
        }
        //Return the finally consrcuted User object
        public Todo build() {
            Todo todo =  new Todo(this);
            return todo;
        }
    }
}
