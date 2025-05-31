# Andersson David Sánchez Méndez Grupo 3

# ECISalud - Medical Appointments Management System

## Description
ECISalud is a web API for managing medical appointments at ECI Salud Vital. This system allows patients to schedule appointments with specialists, manage their appointments, and provides administrative capabilities for healthcare providers.

## Architecture

### Class Diagram
```mermaid
classDiagram
    class Appointment {
        +String id
        +String patientName
        +String patientId
        +String email
        +LocalDate date
        +String specialty
        +AppointmentStatus status
    }

    class AppointmentStatus {
        <<enumeration>>
        PENDING
        CONFIRMED
        CANCELLED
    }

    class Specialty {
        +String id
        +String name
        +String description
    }

    class AppointmentRepository {
        +List~Appointment~ findByEmail(String email)
        +List~Appointment~ findByEmailAndStatus(String email, AppointmentStatus status)
    }

    class SpecialtyRepository {
    }

    class AppointmentService {
        +List~Appointment~ getAllAppointments()
        +Optional~Appointment~ getAppointmentById(String id)
        +List~Appointment~ getAppointmentsByEmail(String email)
        +List~Appointment~ getAppointmentsByEmailAndStatus(String email, AppointmentStatus status)
        +Appointment createAppointment(AppointmentRequestDTO appointmentRequest)
        +Appointment cancelAppointment(String id)
    }

    class SpecialtyService {
    }

    class AppointmentController {
        +ResponseEntity~List~Appointment~~ getAllAppointments()
        +ResponseEntity~Appointment~ createAppointment(AppointmentRequestDTO appointmentDTO)
        +ResponseEntity~Appointment~ cancelAppointment(String id)
    }

    class SpecialtyController {
    }

    class AppointmentDTO {
    }

    class AppointmentRequestDTO {
        +String patientName
        +String patientId
        +String email
        +LocalDate date
        +String specialtyId
    }

    class SpecialtyDTO {
    }

    AppointmentController --> AppointmentService
    SpecialtyController --> SpecialtyService
    AppointmentService --> AppointmentRepository
    AppointmentService --> SpecialtyRepository
    SpecialtyService --> SpecialtyRepository
    AppointmentController ..> AppointmentRequestDTO
    Appointment *-- AppointmentStatus
```
![alt text](assets/image.png)

### Component Diagram
```mermaid
flowchart TB
    subgraph "Client Layer"
        Client[Client Applications/Postman]
    end

    subgraph "API Layer"
        RestControllers[REST Controllers]
        Swagger[Swagger Documentation]
    end

    subgraph "Business Logic Layer"
        Services[Services]
        DTOs[Data Transfer Objects]
        Validation[Validation]
    end

    subgraph "Persistence Layer"
        Repositories[MongoDB Repositories]
        Models[Domain Models]
    end

    subgraph "Data Storage Layer"
        MongoDB[(MongoDB Atlas)]
    end

    Client <--> RestControllers
    Client <--> Swagger
    RestControllers <--> Services
    Services <--> DTOs
    Services <--> Validation
    Services <--> Repositories
    Repositories <--> Models
    Repositories <--> MongoDB
```

## Technologies Used

### Backend
- **Spring Boot 3.5.0**: Framework for creating stand-alone, production-grade Spring-based applications
- **Spring Data MongoDB**: Easy integration with MongoDB databases
- **Spring Validation**: For data validation
- **SpringDoc OpenAPI**: For API documentation with Swagger UI
- **Lombok**: To reduce boilerplate code
- **Maven**: Dependency management and build tool
- **JUnit 5**: For unit testing
- **MongoDB**: NoSQL database for data storage
- **Java 17**: Programming language

## API Documentation

### Swagger UI
The API documentation is available via Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```

## Backend Implementation

### Controllers
- **AppointmentController**: REST endpoints for managing appointments
  - GET /api/appointments: List all appointments
  - POST /api/appointments: Create a new appointment
  - PUT /api/appointments/{id}/cancel: Cancel an appointment

- **SpecialtyController**: REST endpoints for managing medical specialties

### Services
- **AppointmentService**: Business logic for appointment management
  - Get all appointments
  - Get appointment by ID
  - Get appointments by email
  - Get appointments by email and status
  - Create appointment
  - Cancel appointment

- **SpecialtyService**: Business logic for medical specialty management

### Models
- **Appointment**: Represents a medical appointment with patient details, date, specialty, and status
- **Specialty**: Represents a medical specialty with name and description

### Data Transfer Objects (DTOs)
- **AppointmentRequestDTO**: For creating new appointments with validation
- **AppointmentDTO**: For transferring appointment data
- **SpecialtyDTO**: For transferring specialty data

### Validation
Input validation is implemented using Jakarta Validation annotations:
- @NotBlank: For required string fields
- @Email: For validating email format
- @NotNull: For required non-string fields

### Repositories
MongoDB repositories for data access:
- **AppointmentRepository**: For appointment data access
- **SpecialtyRepository**: For specialty data access

## Setup and Running Instructions

### Prerequisites
- Java 17
- Maven
- MongoDB instance (local or cloud-based)

### Configuration
Connection to MongoDB is configured in application.properties:
```properties
spring.application.name=ECISalud
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/ecisalud
```

### Running the Application
```bash
mvn spring-boot:run
```

## Example API Requests

### Create Appointment
```http
POST /api/appointments
Content-Type: application/json

{
    "patientName": "John Doe",
    "patientId": "1234567890",
    "email": "john.doe@example.com",
    "date": "2023-06-15",
    "specialtyId": "64a3e2b7c4a7410001234567"
}
```

### Get All Appointments
```http
GET /api/appointments
```

### Cancel Appointment
```http
PUT /api/appointments/64a3e2b7c4a7410001234567/cancel
```

## Project Structure
```
ECISalud/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/eci/cvds/ECISalud/
│   │   │       ├── config/
│   │   │       │   └── SwaggerConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── AppointmentController.java
│   │   │       │   └── SpecialtyController.java
│   │   │       ├── dto/
│   │   │       │   ├── AppointmentDTO.java
│   │   │       │   ├── AppointmentRequestDTO.java
│   │   │       │   └── SpecialtyDTO.java
│   │   │       ├── model/
│   │   │       │   ├── Appointment.java
│   │   │       │   └── Specialty.java
│   │   │       ├── repository/
│   │   │       │   ├── AppointmentRepository.java
│   │   │       │   └── SpecialtyRepository.java
│   │   │       ├── service/
│   │   │       │   ├── AppointmentService.java
│   │   │       │   └── SpecialtyService.java
│   │   │       └── EciSaludApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── edu/eci/cvds/ECISalud/
│               └── EciSaludApplicationTests.java
└── pom.xml
```

## POSTMAN TESTING

![alt text](assets/image-1.png)

![alt text](assets/image-2.png)

![alt text](assets/image-3.png)


# Swagger Docs

![alt text](assets/image-4.png)