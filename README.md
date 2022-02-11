# Tasks list API

> REST API for **Tasks list** application. Simple to-do list system.

> Spring Boot + JPA / Hibernate + H2 Database

> *Trabalho final da disciplina de **Técnicas de Progrmação 2** da **Universidade Federal do Ceará***. [Vídeo explicando o projeto](https://youtu.be/K9VQvBDPly8).

## Introduction

### Overview

 - Creates and manages task and label entities.
 - Includes CRUD requests for all entities.
  

### Authentication

No authentication required


## Installation and Setup Instructions
### Requirements

 - [ ] JDK 11 
 - [ ] Maven 4.0+
 - [ ] Any IDE supporting Spring Framework

Clone this repository with 
`git clone https://github.com/natyncaldas/todo-list-crud.git`

Access project folder with
`cd todo-list-crud`

Next, import the Maven project into your chosen IDE. Then, run `mvn clean install` . 
On **src/main/resources/application.properties**, add the following code:

```properties
spring.datasource.url=jdbc:h2:mem:todo
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
Finally, run **src/main/java/com/list/tasks/TaskApplication.java**
The API will run on: [localhost:8080/api/v1](https://localhost:8080/api/v1)

## API Endpoints Info

### Task
|HTTP Method| Path | Params | Body | Success | Description
|--|--|--|--|-- | --
| `GET` | /tasks | None | None | 200 | Get all tasks
| `GET` | /tasks/{id} | None | None | 200 | Get task by ID
| `POST` | /tasks | None | *Examples below* | 201 | Create new task
| `PUT` | /tasks/{id} | None | *Examples below* | 200 | Edit task by ID
| `DELETE` | /tasks/{id} | None | None | 204 | Delete task by ID

### Label
|HTTP Method| Path | Params | Body | Success | Description
|--|--|--|--|-- | --
| `GET` | /labels | None | None | 200 | Get all labels
| `GET` | /labels/{id} | None | None | 200 | Get label by ID
| `POST` | /labels | None | *Examples below* | 201 | Create new label
| `PUT` | /labels/{id} | None | *Examples below* | 200 | Edit label by ID
| `DELETE` | /labels/{id} | None | None | 204 | Delete label by ID


## `POST` & `PUT` JSON Examples

```json
//TASK
{
  "title": "Code API",
  "description": "Using spring boot",
  "taskStatus": "TO_DO",
  "taskPriority": "HIGH",
  "label": {
        "title": "Code",
        "labelColor": "PINK"
    }
}
//LABEL 
{
  "title": "Test",
  "labelColor": "ORANGE"
}

```
