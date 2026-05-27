# Auth Service - Hexagonal Architecture
Project currently under development.
Backend authentication microservice built with Spring Boot using Hexagonal Architecture principles.

## Features

- Login endpoint
- Hexagonal architecture
- REST API
- Layer separation (domain, application, infrastructure)
- Token generation flow

## Tech Stack

- Java 17
- Spring Boot
- Maven
- REST APIs

## Architecture

This project follows Hexagonal Architecture:

- Domain
- Application
- Infrastructure
- Ports & Adapters

## Endpoint

### Login

```http
POST /auth/login
