# Competitive Capabilities International Assessment

CCI Assessment is a rest api built in Java as part of testing my ability as a software developer.
The background of this application is driven from an assessment that forms part of the interview process not solving any business real problems

## Requirements

* IDE or text editor
* JDK 1.8+
* Maven 3+
* Msql database
* Postman or any relevant tool

The building of the endpoints are driven from the assessment exposed points as below:


---

#### Create user
```sh
curl --location --request POST 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "jsmith",
    "first_name": "John",
    "last_name": "Smith"
}'
```

#### Update user
```sh
curl --location --request PUT 'http://localhost:8080/api/users/{id}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first_name": "John",
    "last_name": "Doe"
}'
```

#### List all users
```sh
curl --location --request GET 'http://localhost:8080/api/users'
```

#### Get User info
```sh
curl --location --request GET 'http://localhost:8080/api/users/{id}'
```
Expecting this structure (for the User):
```
{ 
  "id": 1,
  "username": "jsmith",
  "first_name": "James",
  "last_name": "Smith"
}
```

#### Create Task
```sh
curl --location --request POST 'http://localhost:8080/api/users/{id}/tasks' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "My task",
    "description": "Description of task",
    "date_time": "2016-05-25 14:25:00"
}'
```

#### Update Task
```sh
curl --location --request PUT 'http://localhost:8080/api/users/{user_id}/tasks/{task_id}' \
--header 'Content-Type: application/json' \
--data-raw '{"name":"My updated task"}'
```

#### Delete Task
```sh
curl --location --request DELETE 'http://localhost:8080/api/users/{user_id}/tasks/{task_id}'
```

#### Get Task Info
```sh
curl --location --request GET 'http://localhost:8080/api/users/{user_id}/tasks/{task_id}'
```

#### List all tasks for a user

```sh
curl --location --request GET 'http://localhost:8080/api/users/{user_id}/tasks'
```
