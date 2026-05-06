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
1. Ensure Java 17, Maven, Docker Desktop, and Supabase CLI are installed.
2. Start local Supabase:
   ```bash
   supabase start
   ```
3. Apply migrations + seed data:
   ```bash
   supabase db reset
   ```
4. Create a local .env file (see .env.example) and set:
   ```
   PORT=8081
   SPRING_PROFILES_ACTIVE=prod
   JDBC_URL=jdbc:postgresql://127.0.0.1:54322/postgres
   DB_USER=postgres
   DB_PASSWORD=postgres
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
6. Swagger UI: http://localhost:8081/swagger-ui.html

## Local Supabase (Database)
- Supabase Studio: http://127.0.0.1:54323
- Project URL: http://127.0.0.1:54321

## Environment Variables
- PORT
- SPRING_PROFILES_ACTIVE
- JDBC_URL
- DB_USER
- DB_PASSWORD

## Profiles
- dev: H2 in-memory database
- prod: PostgreSQL (Supabase)

Default profile is set via .env:
```
SPRING_PROFILES_ACTIVE=prod
```

Windows PowerShell (one-off override):
```powershell
$env:SPRING_PROFILES_ACTIVE="dev"
mvn spring-boot:run
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

## Switch From Local Supabase to Supabase Cloud
1. Create a Supabase project in the dashboard.
2. Link the local repo to your cloud project:
   ```bash
   supabase login
   supabase link --project-ref <your-project-ref>
   ```
3. Push local migrations to the cloud database:
   ```bash
   supabase db push
   ```
4. Update .env to use the cloud connection string:
   ```
   JDBC_URL=jdbc:postgresql://<project-ref>.supabase.co:5432/postgres?sslmode=require
   DB_USER=postgres
   DB_PASSWORD=<your-cloud-db-password>
   ```

## Notes
- Conflict detection uses: (start < existingEnd) AND (end > existingStart)
- CORS is enabled for all origins (demo friendly).
