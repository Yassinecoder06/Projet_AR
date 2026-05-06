# smart-reservation-api

Production-ready Spring Boot REST API for managing users, rooms, and reservations with conflict detection.

## Description
Smart Reservation API provides user management, room management, and a reservation system with strict overlap detection to prevent double-booking.

## Architecture
Layered architecture:
- controller
- service
- repository
- entity
- dto
- exception
- config

## Tech Stack
- Java 17
- Spring Boot
- Maven
- Spring Web (REST API)
- Spring Data JPA
- PostgreSQL (Supabase compatible)
- H2 (dev)
- OpenAPI / Swagger

## API Endpoints
Users:
- GET /users
- POST /users

Rooms:
- GET /rooms
- POST /rooms
- GET /rooms/{id}/available?date=YYYY-MM-DD&start=HH:mm&end=HH:mm

Reservations:
- GET /reservations
- POST /reservations
- DELETE /reservations/{id}

## Setup Instructions (Local)
1. Ensure Java 17 and Maven are installed.
2. Create a local .env file (see .env.example).
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Swagger UI: http://localhost:8080/swagger-ui.html

## Environment Variables
- PORT
- JDBC_URL
- DB_USER
- DB_PASSWORD

## Profiles
- dev: H2 in-memory database
- prod: PostgreSQL (Supabase)

To run with a profile:
```bash
SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run
```

## Deployment (Render + Supabase)
1. Push the project to GitHub.
2. Create a new Web Service in Render and connect the repo.
3. Set environment variables in Render:
   - PORT
   - JDBC_URL (include sslmode=require for Supabase)
   - DB_USER
   - DB_PASSWORD
4. Build command:
   ```bash
   mvn clean install
   ```
5. Start command:
   ```bash
   java -jar target/*.jar
   ```

## Notes
- Conflict detection uses: (start < existingEnd) AND (end > existingStart)
- CORS is enabled for all origins (demo friendly).
