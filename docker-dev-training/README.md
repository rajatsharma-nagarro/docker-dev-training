# User Management Service

## Overview

This project is a simple User Management Service built with **Spring Boot**. It provides RESTful APIs for basic user operations such as registration, login, and user retrieval. The service demonstrates best practices for DTO-to-entity mapping, service-layer testing, and clean architecture.

## Features

- Register a new user
- Login with username and password
- Fetch user details by ID
- Uses DTOs for request handling
- Efficient mapping using Jackson's `ObjectMapper`
- Unit tests with JUnit 5 and Mockito

## Technology Stack

- Java 17+
- Spring Boot
- Maven
- Jackson (for object mapping)
- JUnit 5 & Mockito (for testing)

## Why This Project?

This project serves as a boilerplate for user management in Java-based microservices. It demonstrates:
- Clean separation of concerns (DTOs, entities, services, repositories)
- Efficient and maintainable mapping strategies
- How to write unit tests for service layers

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- (Optional) Docker for containerization

### Setup

1. **Clone the repository:**