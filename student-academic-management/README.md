# KIET Student Academic Management REST API

Spring Boot Maven project matching the six questions in the mock assessment. It uses an in-memory `List<Student>` and does not require a database.

## Requirements
- JDK 17 or later
- IntelliJ IDEA with Maven support

## Run in IntelliJ
1. Extract/open this project folder in IntelliJ IDEA.
2. Set the Project SDK to JDK 17.
3. Wait for Maven to import dependencies.
4. Run `src/main/java/com/kiet/student/StudentApplication.java`.
5. The server starts on port `8080` and the three startup messages appear in the console.

## Endpoints
- `GET    http://localhost:8080/students`
- `GET    http://localhost:8080/students/101`
- `POST   http://localhost:8080/students`
- `PUT    http://localhost:8080/students/101`
- `DELETE http://localhost:8080/students/105`
- `GET    http://localhost:8080/students/search?branch=CSE&year=2`
- `GET    http://localhost:8080/students/topper?cgpa=9.0`

POST request body example:
```json
{
  "studentId": 106,
  "name": "Amit Kumar",
  "email": "amit@kiet.edu",
  "branch": "CSE(AI)",
  "year": 2,
  "cgpa": 8.75
}
```

PUT request body example:
```json
{
  "studentId": 101,
  "name": "Rahul Sharma",
  "email": "rahul@kiet.edu",
  "branch": "CSE(AI & ML)",
  "year": 3,
  "cgpa": 9.05
}
```

Note: this mock intentionally uses in-memory storage. Changes reset when the application restarts.
