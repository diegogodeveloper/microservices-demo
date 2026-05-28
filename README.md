# Auth Service - Hexagonal Architecture

Backend authentication microservice built with Spring Boot using Hexagonal Architecture principles.

Project currently under development.

---

## Features

- JWT authentication
- Spring Security integration
- Stateless authentication
- Login endpoint
- Protected endpoints
- Global exception handling
- Request validation
- OpenAPI / Swagger documentation
- Hexagonal architecture
- Layer separation (domain, application, infrastructure)

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JJWT)
- Maven
- OpenAPI / Swagger
- REST APIs

---

## Architecture

This project follows Hexagonal Architecture principles:

- Domain
- Application
- Infrastructure
- Ports & Adapters
- Stateless authentication with JWT

---

## Endpoints

### Login

```http
POST /auth/login