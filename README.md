# Rider Service

A production-ready Spring Boot microservice for managing riders in a Ride-Hailing System.

## Tech Stack
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Maven
- Docker
- Lombok

## Architecture
Follows clean architecture with layered structure:
- Controller → Service → Repository → Database

## Package Structure
```
com.rides.riderservice
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```

## Base URL
`http://localhost:8081/v1/riders`

## Database
- Name: `rider_db`
- Table: `riders`
- Schema: rider_id (UUID), name, email, phone, city, created_at

## APIs
- POST /v1/riders - Create Rider
- GET /v1/riders - Get All Riders
- GET /v1/riders/{id} - Get Rider by ID
- PUT /v1/riders/{id} - Update Rider
- DELETE /v1/riders/{id} - Delete Rider

## Setup
1. Ensure PostgreSQL is running with database `rider_db`, user `postgres`, password `postgres`.
2. Run `mvn clean install`
3. Run `mvn spring-boot:run`

## Docker
Build and run with Docker:
```bash
docker build -t rider-service .
docker run -p 8081:8081 rider-service
```

## Postman
Import `RiderServiceAPI.postman_collection.json` for testing.

## Actuator
- Health: `http://localhost:8081/actuator/health`
- Metrics: `http://localhost:8081/actuator/metrics`