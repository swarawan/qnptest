# QNP Test

## Prerequisites

1. Installed MySQL
2. Create a database with db name `qnp`
3. No worry with the table, all handled by Flyway
---

## How to run

1. Open file `/src/main/resources/application.properties` and set your own `DB_HOST`, `DB_USERNAME`, and `DB_PASSWORD`.
2. Inside the root folder execute `./gradlew build` to compile the program.
3. Then execute `./gradlew bootRun` to run the binary.
---

## Assignments
* [x] CRUD operations on 1 entity
* [x] Fetch data concurrently
* [ ] Build a rate limiter
* [x] Jwt Validations

## API
### [POST] QNP - Login Default
URL : http://localhost:8080/login/default

Response:
```json
{
    "access_token": "access-token"
}
``` 

### [POST] QNP - Create
URL: http://localhost:8080/contacts

Header(s):
- Authorization: Bearer <access-token>

Request:
```json
{
    "name": "RIO",
    "email": "rio.jogja3@mail.com",
    "address": "JOGJA",
    "phone_number": "085729891040"
}
```

Response:
```json
{
    "id": 1,
    "name": "RIO",
    "email": "rio.jogja3@mail.com",
    "address": "JOGJA",
    "phone_number": "085729891040",
    "createdAt": "2024-05-08T22:26:55.241535",
    "updatedAt": "2024-05-08T22:26:55.241602"
}
``` 

### [PUT] QNP - Update
URL: http://localhost:8080/contacts/{id}

Header(s):
- Authorization: Bearer <access-token>

Request:
```json
{
  "name": "RIO UPDATE 1",
  "address": "JOGJA",
  "phone_number": "085729891040"
}
```

Response:
```json
{
  "id": 1,
  "name": "RIO UPDATE 1",
  "email": "rio.jogja123@mail.com",
  "address": "JOGJA",
  "phone_number": "085729891040",
  "createdAt": "2024-05-08T22:26:55",
  "updatedAt": "2024-05-08T22:35:14.860842"
}
``` 

### [GET] QNP - Read All
URL: http://localhost:8080/contacts

Header(s):
- Authorization: Bearer <access-token>

Response:
```json
[
  {
    "id": 1,
    "name": "RIO",
    "email": "rio.jogja2@mail.com",
    "address": "JOGJA",
    "phone_number": "085729891040",
    "createdAt": "2024-05-08T21:19:28",
    "updatedAt": "2024-05-08T21:19:28"
  },
  {
    "id": 2,
    "name": "RIO",
    "email": "rio.jogja3@mail.com",
    "address": "JOGJA",
    "phone_number": "085729891040",
    "createdAt": "2024-05-08T21:23:51",
    "updatedAt": "2024-05-08T21:23:51"
  }
]
``` 

### [GET] QNP - Read One
URL: http://localhost:8080/contacts/{id}

Header(s):
- Authorization: Bearer <access-token>

Response:
```json
{
  "id": 1,
  "name": "RIO UPDATE 1",
  "email": "rio.jogja123@mail.com",
  "address": "JOGJA",
  "phone_number": "085729891040",
  "createdAt": "2024-05-08T22:26:55",
  "updatedAt": "2024-05-08T22:35:15"
}
``` 

### [DELETE] QNP - Delete
URL: http://localhost:8080/contacts/{id}

Header(s):
- Authorization: Bearer <access-token>

Response:
```json
{
  "success": true,
  "message": "Data deleted"
}
``` 

### [GET] QNP - Get JsonPlaceholder 
URL: http://localhost:8080/json_placeholder

Header(s):
- Authorization: Bearer <access-token>

Response:
```json
{
  "total_time": "737 ms",
  "users": "<post-json-from-external-url>",
  "posts": "<post-json-from-external-url>"
}
``` 