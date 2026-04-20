# Movie Booking Platform

A microservices-based Movie Booking Platform built using Java 17 and Spring Boot.

## Implemented Features

- Browse shows by movie and date
- Book seats with concurrency-safe locking
- API Gateway routing
- Separate microservices architecture

## Services

- **api-gateway** → Port 8080  
- **show-service** → Port 8082  
- **seat-service** → Port 8083  

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Cloud Gateway
- Maven
- H2 Database

## Clone Project

```bash
git clone https://github.com/watpadepriyanka/movie-booking-platform.git
cd movie-booking-platform
