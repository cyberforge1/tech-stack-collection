# Express React Template

# Project MVP

## Frontend

- (/) A landing page that contains:
    - value passed from the backend (ex. Home Page)
    - button redirecting to the form page

- (/form) A form page that contains:
    - title	
    - input field
    - ‘add todo’ button
    - dynamic display of todos in the database
    - dynamically rendered ‘update todo’ button
    - dynamically rendered delete todo’ button

## Backend


- URL endpoint to test access (ex. /helloworld)
- A value passed to the frontend directly as a string from the backend (w/o db access)
- Create, read, update and delete endpoints

```http
GET /helloworld
GET /
GET /todo
GET /todo/:id
POST /todo
PUT /todo/:id
DELETE /todo/:id
```

## Database

- Filled with one value automatically 
- Transitioning from a local to a cloud-base database upon deployment
- Either SQL or NoSQL solutions
- Fundamentally simple design

```sql
CREATE TABLE todos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255)
);
```