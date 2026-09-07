# Kolesa.kz Spring Project

A Kolesa.kz-inspired car marketplace application built with Spring Boot. The project started as a simple CRUD exercise and is being evolved into a production-style Java backend.

## Features

- Car and country management
- Server-side rendered UI with Thymeleaf
- REST API for cars and countries
- Service layer with transactional boundaries
- JPA/Hibernate persistence
- Bean Validation
- Centralized REST exception handling
- PostgreSQL support
- Docker and Docker Compose
- Actuator health endpoint
- Environment-based database configuration

## Architecture

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
```

The application keeps web concerns in controllers and business/persistence operations in services and repositories. REST endpoints are separated from the Thymeleaf MVC flow.

## REST API

### Cars

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/{id}` | Get a car |
| POST | `/api/cars?countryId={id}` | Create a car |
| PUT | `/api/cars/{id}?countryId={id}` | Update a car |
| DELETE | `/api/cars/{id}` | Delete a car |

### Countries

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/countries` | Get all countries |
| GET | `/api/countries/{id}` | Get a country |
| POST | `/api/countries` | Create a country |
| DELETE | `/api/countries/{id}` | Delete a country |

## Run locally

Requirements:

- Java 25
- PostgreSQL 17+
- Gradle Wrapper

Set these environment variables:

```text
DB_URL=jdbc:postgresql://localhost:5432/kolesokz
DB_USERNAME=postgres
DB_PASSWORD=your_password
SERVER_PORT=8000
```

Then run:

```bash
./gradlew bootRun
```

Windows:

```powershell
./gradlew.bat bootRun
```

The application is available on `http://localhost:8000`.

## Docker

Start PostgreSQL and the application with:

```bash
docker compose up --build
```

## Health check

```text
GET /actuator/health
```

## Project goals

The long-term goal is to turn this educational project into a portfolio-quality backend demonstrating clean architecture, REST API design, validation, testing, security, database migrations, pagination/filtering and CI/CD.
