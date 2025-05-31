# 🏥 ECISalud - Medical Appointments Management System

<div align="center">
  
  ![ECISalud Logo](assets/image.png)
  
  _Author: **Andersson David Sánchez Méndez** - Group 3_
  
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
  [![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green.svg)](https://www.mongodb.com/atlas/database)
  [![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
  [![Azure](https://img.shields.io/badge/Azure-Deployed-blue.svg)](https://azure.microsoft.com/)
  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
  [![JaCoCo](https://img.shields.io/badge/Code%20Coverage-60%25-yellow.svg)](https://www.jacoco.org/jacoco/)
  [![Swagger](https://img.shields.io/badge/API%20Docs-Swagger-85EA2D.svg)](https://swagger.io/)
  
</div>

## 📋 Description
ECISalud is a robust web API for managing medical appointments at ECI Salud Vital clinic. This system allows patients to schedule appointments with specialists, manage their appointments, and provides administrative capabilities for healthcare providers. The system is deployed on Azure and connected to MongoDB Atlas for data persistence.

## 🏗️ Architecture

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
![Architecture Diagram](assets/image.png)

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

## 🔧 Technologies Used

### Backend
- **Spring Boot 3.5.0**: Framework for creating stand-alone, production-grade Spring-based applications
- **Spring Data MongoDB**: Easy integration with MongoDB databases
- **Spring Validation**: For data validation
- **SpringDoc OpenAPI**: For API documentation with Swagger UI
- **Lombok**: To reduce boilerplate code (manually implemented getters/setters due to compatibility issues)
- **Maven**: Dependency management and build tool
- **JUnit 5**: For unit testing with extensive coverage
- **MongoDB Atlas**: Cloud NoSQL database for data storage
- **Java 17**: Programming language
- **Azure App Service**: For cloud deployment
- **JaCoCo**: For test coverage reporting
- **Cross-Origin Resource Sharing (CORS)**: Enabled for all origins to facilitate frontend integration

## 📊 API Documentation

### Swagger UI
The API documentation is available via Swagger UI at:
```
https://god-fwcafqgvhvbdfthh.canadacentral-01.azurewebsites.net/swagger-ui/index.html
```

For local testing:
```
http://localhost:8081/swagger-ui/index.html
```

## ⚙️ Backend Implementation

### Controllers
- **AppointmentController**: REST endpoints for managing appointments
  - `GET /api/appointments`: List all appointments
  - `GET /api/appointments/email/{email}`: Get appointments by email
  - `GET /api/appointments/filter?email={email}&status={status}`: Filter appointments by email and status
  - `POST /api/appointments`: Create a new appointment
  - `PUT /api/appointments/{id}/cancel`: Cancel an appointment

- **SpecialtyController**: REST endpoints for managing medical specialties
  - `GET /api/specialties`: Get all specialties
  - `GET /api/specialties/{id}`: Get specialty by ID

### Services
- **AppointmentService**: Business logic for appointment management
  - Get all appointments
  - Get appointments by email
  - Get appointments by email and status
  - Create appointment with date validation
  - Cancel appointment

- **SpecialtyService**: Business logic for medical specialty management
  - Get all specialties
  - Get specialty by ID

### Models
- **Appointment**: Represents a medical appointment with patient details, date, specialty, and status
- **Specialty**: Represents a medical specialty with name, description, doctor, location, and image URL

### Data Transfer Objects (DTOs)
- **AppointmentRequestDTO**: For creating new appointments with validation
- **AppointmentDTO**: For transferring appointment data
- **SpecialtyDTO**: For transferring specialty data

### Validation
Input validation is implemented using Jakarta Validation annotations:
- `@NotBlank`: For required string fields
- `@Email`: For validating email format
- `@NotNull`: For required non-string fields
- Custom validation in service layer for appointment dates (rejects past dates)

### Repositories
MongoDB repositories for data access:
- **AppointmentRepository**: For appointment data access
- **SpecialtyRepository**: For specialty data access

### Data Initialization
- **DataInitializer**: Automatically populates the database with the required specialties on application startup:
  - Medicina General
  - Psicología
  - Ortopedia
  - Odontología

## 🚀 Setup and Running Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- MongoDB instance (local or cloud-based)

### Configuration
![MongoDB Configuration](assets/image-4.png)

### Running Locally
1. Clone the repository
2. Configure MongoDB connection in `application.properties`
3. Run with Maven:
   ```bash
   mvn spring-boot:run
   ```
   
### Testing
Run the tests with:
```bash
mvn test
```

Coverage reports will be generated in the `target/site/jacoco` directory.

## 🔄 CI/CD

The application is set up with a CI/CD pipeline that:
- Builds the application
- Runs unit tests
- Verifies code coverage (minimum 60%)
- Deploys to Azure App Service

## 🌐 Deployment

The backend is deployed on Azure App Service and is accessible at:
```
https://god-fwcafqgvhvbdfthh.canadacentral-01.azurewebsites.net/
```

## 📸 POSTMAN TESTING

![POSTMAN Testing](assets/image-1.png)

![POSTMAN Testing](assets/image-2.png)

![POSTMAN Testing](assets/image-3.png)

## 📑 Swagger Docs

![Swagger Documentation](assets/image-4.png)
