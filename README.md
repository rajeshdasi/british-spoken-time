<div align="center">

# British Spoken Time Converter

### A Spring Boot REST API that converts digital time format (HH:mm) to spoken time format in multiple languages

[![CI/CD Pipeline](https://github.com/rajeshdasi/british-spoken-time/actions/workflows/ci.yml/badge.svg)](https://github.com/rajeshdasi/british-spoken-time/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/rajeshdasi/british-spoken-time/branch/main/graph/badge.svg)](https://codecov.io/gh/rajeshdasi/british-spoken-time)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg)](https://spring.io/projects/spring-boot)

[Features](#features) • [Quick Start](#quick-start) • [API Documentation](#api-documentation) • [Deployment](#deployment) • [Contributing](#contributing)

</div>

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Conversion Examples](#conversion-examples)
- [Architecture](#architecture)
- [Technical Stack](#technical-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [API Documentation](#api-documentation)
- [Deployment](#deployment)
  - [Docker](#docker-deployment)
  - [Kubernetes](#kubernetes-deployment)
- [Configuration](#configuration)
- [Testing](#testing)
- [Code Quality](#code-quality)
- [CI/CD Pipeline](#cicd-pipeline)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The British Spoken Time Converter is a production-ready REST API that transforms digital time formats into natural spoken language. Built with Spring Boot and following industry-standard design patterns, it provides a scalable, maintainable solution for time conversion across multiple languages.

### Why This Project?

- **Multi-language Support**: Currently supports British English and German, with extensible architecture for additional locales
- **Production Ready**: Includes Docker, Kubernetes manifests, health checks, and monitoring
- **High Quality**: 80%+ test coverage, comprehensive test suite with 193 tests
- **Best Practices**: Follows SOLID principles, Spring best practices, and industry-standard design patterns

---

## Features

- ✅ Convert digital time to spoken English (British) and German
- ✅ RESTful API with Spring Boot WebFlux (reactive)
- ✅ Extensible architecture supporting multiple locales
- ✅ Comprehensive unit and integration tests (193 tests)
- ✅ Code coverage with Jacoco (80%+ coverage)
- ✅ Code quality checks with Checkstyle
- ✅ CI/CD pipeline with GitHub Actions
- ✅ Built with Java 21 LTS
- ✅ Spring-managed components with dependency injection
- ✅ Docker and Kubernetes support
- ✅ Health checks and monitoring endpoints
- ✅ Production-ready deployment configurations

---

## Conversion Examples

### British English (en_GB)

| Input   | Output                  | Description               |
|---------|-------------------------|---------------------------|
| 1:00    | one o'clock            | Hour with o'clock         |
| 2:05    | five past two          | Minutes past              |
| 3:10    | ten past three         | Minutes past              |
| 4:15    | quarter past four      | Quarter past              |
| 5:20    | twenty past five       | Minutes past              |
| 6:25    | twenty five past six   | Minutes past              |
| 6:32    | six thirty two         | Casual format             |
| 7:30    | half past seven        | Half past                 |
| 7:35    | twenty five to eight   | Minutes to                |
| 8:40    | twenty to nine         | Minutes to                |
| 9:45    | quarter to ten         | Quarter to                |
| 10:50   | ten to eleven          | Minutes to                |
| 11:55   | five to twelve         | Minutes to                |
| 0:00    | midnight               | Special case              |
| 12:00   | noon                   | Special case              |

### German (de_DE)

| Input   | Output                      | Description               |
|---------|-----------------------------|---------------------------|
| 1:00    | eins Uhr                    | Hour (Uhr)                |
| 2:05    | fünf Minuten nach zwei      | Minutes after             |
| 3:10    | zehn Minuten nach drei      | Minutes after             |
| 3:15    | Viertel nach drei           | Quarter past              |
| 5:20    | zwanzig Minuten nach fünf   | Minutes after             |
| 9:30    | halb zehn                   | Half to next hour         |
| 8:40    | zwanzig Minuten vor neun    | Minutes before            |
| 9:45    | Viertel vor zehn            | Quarter to                |
| 10:50   | zehn Minuten vor elf        | Minutes before            |
| 11:55   | fünf Minuten vor zwölf      | Minutes before            |
| 0:00    | Mitternacht                 | Special case              |
| 12:00   | Mittag                      | Special case              |

> **Note**: In German, "halb zehn" (half ten) means 9:30 - half to ten, not half past nine.

---

## Architecture

### Design Patterns

This project implements several industry-standard design patterns:

- **Strategy Pattern**: `MinuteHandler` interface with multiple implementations
- **Factory Pattern**: `TimeConverterFactory` for locale-based converter creation
- **Chain of Responsibility**: Handler chain for processing different time formats
- **Dependency Injection**: Constructor-based DI throughout
- **Service Layer Pattern**: Clear separation of concerns

### SOLID Principles

- **Single Responsibility**: Each handler has one specific purpose
- **Open/Closed**: New locales can be added without modifying existing code
- **Liskov Substitution**: All handlers are interchangeable
- **Interface Segregation**: Focused, minimal interfaces
- **Dependency Inversion**: Depends on abstractions, not concrete classes

---

## Technical Stack

| Category            | Technology                |
|---------------------|---------------------------|
| **Language**        | Java 21 (LTS)             |
| **Framework**       | Spring Boot 3.4.1         |
| **Reactive**        | Spring WebFlux            |
| **Build Tool**      | Maven 3.8+                |
| **Testing**         | JUnit 5, Spring Boot Test |
| **Code Coverage**   | Jacoco                    |
| **Code Quality**    | Checkstyle                |
| **CI/CD**           | GitHub Actions            |
| **Containerization**| Docker                    |
| **Orchestration**   | Kubernetes                |

---

## Prerequisites

### Required

- **JDK 21** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8** or higher ([Download](https://maven.apache.org/download.cgi))

### Optional (for deployment)

- **Docker** ([Download](https://www.docker.com/get-started))
- **Kubernetes cluster** (Minikube, Kind, Docker Desktop, or cloud provider)
- **kubectl** ([Install](https://kubernetes.io/docs/tasks/tools/))

---

## Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/rajeshdasi/british-spoken-time.git
cd british-spoken-time
```

### 2. Build the Project

> **Important**: The project includes a custom Maven settings file (`.mvn/settings.xml`) to ensure correct dependency resolution. Always include `-s .mvn/settings.xml` in your Maven commands.

```bash
mvn clean install -s .mvn/settings.xml
```

### 3. Run Tests

```bash
mvn test -s .mvn/settings.xml
```

### 4. Run the Application

```bash
java -jar target/british-spoken-time-1.0.0.jar
```

Or using Maven:

```bash
mvn spring-boot:run -s .mvn/settings.xml
```

The application will start on `http://localhost:8080`.

### 5. Test the API

```bash
curl "http://localhost:8080/api/time/convert?time=12:00"
```

**Response**:
```json
{
  "spokenTime": "noon",
  "locale": "en_GB"
}
```

---

## API Documentation

### Endpoint: Convert Time

```
GET /api/time/convert
```

#### Parameters

| Parameter | Type   | Required | Description                          | Default |
|-----------|--------|----------|--------------------------------------|---------|
| `time`    | String | Yes      | Time in HH:mm or H:mm format        | -       |
| `locale`  | String | No       | Locale for conversion (en_GB, de_DE) | en_GB   |

#### Request Examples

**British English (default)**:
```bash
curl "http://localhost:8080/api/time/convert?time=12:00"
```

**Explicit locale**:
```bash
curl "http://localhost:8080/api/time/convert?time=12:00&locale=en_GB"
```

**German**:
```bash
curl "http://localhost:8080/api/time/convert?time=9:30&locale=de_DE"
```

#### Response Format

**Success Response** (200 OK):
```json
{
  "spokenTime": "noon",
  "locale": "en_GB"
}
```

**Error Response** (400 Bad Request):
```json
{
  "error": "Invalid time format. Expected HH:mm or H:mm"
}
```

**Error Response** (400 Bad Request) - Unsupported locale:
```json
{
  "error": "Unsupported locale: fr_FR. Supported locales: [en_GB, de_DE]"
}
```

#### Supported Locales

| Locale  | Language        | Description              |
|---------|-----------------|--------------------------|
| `en_GB` | British English | Default locale           |
| `de_DE` | German          | German spoken time       |

---

## Deployment

### Docker Deployment

#### Build Docker Image

```bash
docker build -t british-spoken-time:1.0.0 .
```

#### Run Docker Container

```bash
docker run -d \
  --name british-spoken-time \
  -p 8080:8080 \
  -e TIME_CONVERTER_DEFAULT_LOCALE=en_GB \
  british-spoken-time:1.0.0
```

#### Access the Application

```bash
curl "http://localhost:8080/api/time/convert?time=12:00"
```

#### Stop and Remove Container

```bash
docker stop british-spoken-time
docker rm british-spoken-time
```

#### Docker Compose (Optional)

Create a `docker-compose.yml` file:

```yaml
version: '3.8'
services:
  british-spoken-time:
    build: .
    ports:
      - "8080:8080"
    environment:
      - TIME_CONVERTER_DEFAULT_LOCALE=en_GB
      - SPRING_PROFILES_ACTIVE=production
    restart: unless-stopped
```

Run with Docker Compose:

```bash
docker-compose up -d
```

---

### Kubernetes Deployment

#### Prerequisites

Ensure you have a running Kubernetes cluster and `kubectl` configured.

#### Build and Load Docker Image

For local Kubernetes clusters (Minikube, Kind, Docker Desktop):

```bash
# Build the image
docker build -t british-spoken-time:1.0.0 .

# For Minikube
minikube image load british-spoken-time:1.0.0

# For Kind
kind load docker-image british-spoken-time:1.0.0
```

#### Deploy to Kubernetes

```bash
# Apply ConfigMap
kubectl apply -f k8s/configmap.yaml

# Apply Deployment
kubectl apply -f k8s/deployment.yaml

# Apply Service
kubectl apply -f k8s/service.yaml
```

#### Verify Deployment

```bash
# Check pods
kubectl get pods -l app=british-spoken-time

# Check services
kubectl get svc british-spoken-time

# Check deployment status
kubectl rollout status deployment/british-spoken-time
```

#### Access the Application

**Using ClusterIP Service (default)**:

```bash
# Port forward to access locally
kubectl port-forward svc/british-spoken-time 8080:80

# Test the API
curl "http://localhost:8080/api/time/convert?time=12:00"
```

**Using NodePort Service**:

```bash
# Apply NodePort service
kubectl apply -f k8s/service.yaml

# Get the node IP
kubectl get nodes -o wide

# Access via NodePort (30080)
curl "http://<NODE_IP>:30080/api/time/convert?time=12:00"

# For Minikube
minikube service british-spoken-time-nodeport
```

#### View Logs

```bash
# View logs from all pods
kubectl logs -l app=british-spoken-time -f

# View logs from specific pod
kubectl logs <pod-name>
```

#### Scale the Deployment

```bash
# Scale to 5 replicas
kubectl scale deployment british-spoken-time --replicas=5

# Verify scaling
kubectl get pods -l app=british-spoken-time
```

#### Update Configuration

```bash
# Edit ConfigMap
kubectl edit configmap british-spoken-time-config

# Restart pods to pick up new configuration
kubectl rollout restart deployment/british-spoken-time
```

#### Health Checks

The deployment includes:
- **Liveness Probe**: Checks if the application is alive (`/actuator/health/liveness`)
- **Readiness Probe**: Checks if the application is ready to serve traffic (`/actuator/health/readiness`)
- **Startup Probe**: Checks if the application has started successfully (`/actuator/health`)

#### Resource Limits

The deployment is configured with:
- **Requests**: 256Mi memory, 250m CPU
- **Limits**: 512Mi memory, 500m CPU

#### Clean Up Kubernetes Resources

```bash
# Delete all resources
kubectl delete -f k8s/

# Or delete individually
kubectl delete deployment british-spoken-time
kubectl delete service british-spoken-time british-spoken-time-nodeport
kubectl delete configmap british-spoken-time-config
```

#### Kubernetes Manifest Files

The `k8s/` directory contains:

| File              | Description                              |
|-------------------|------------------------------------------|
| `deployment.yaml` | Deployment configuration with 3 replicas |
| `service.yaml`    | ClusterIP and NodePort services          |
| `configmap.yaml`  | Application configuration                |

#### Customization

Edit `k8s/configmap.yaml` to change default settings:

```yaml
data:
  time.converter.default.locale: "de_DE"  # Change default locale
  LOGGING_LEVEL_COM_TIMECONVERTER: "DEBUG"  # Enable debug logging
```

---

## Configuration

The application can be configured via `application.properties`:

```properties
# Default locale for time conversion
time.converter.default.locale=en_GB

# Server port
server.port=8080

# Logging level
logging.level.com.timeconverter=INFO
```

### Environment Variables (Docker/Kubernetes)

| Variable                          | Description                 | Default |
|-----------------------------------|-----------------------------|---------|
| `TIME_CONVERTER_DEFAULT_LOCALE`   | Default locale for conversion | en_GB   |
| `SPRING_PROFILES_ACTIVE`          | Active Spring profile         | -       |
| `LOGGING_LEVEL_COM_TIMECONVERTER` | Logging level                 | INFO    |

---

## Testing

### Test Structure

The project includes comprehensive tests:

- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test the entire API workflow
- **Parameterized Tests**: Extensive test coverage using JUnit 5's `@ParameterizedTest`

### Test Coverage

| Component                 | Coverage Area                           |
|---------------------------|-----------------------------------------|
| `TimeInput`               | Input parsing and validation            |
| `OClockHandler`           | Zero-minute cases (midnight, noon, o'clock) |
| `PastMinuteHandler`       | Minutes 1-30                            |
| `ToMinuteHandler`         | Minutes 35, 40, 45, 50, 55              |
| `DefaultMinuteHandler`    | Non-standard minute values              |
| `BritishTimeConverter`    | End-to-end conversion logic             |
| `GermanTimeConverter`     | German time conversion logic            |
| `TimeConverterFactory`    | Locale selection                        |
| `TimeConversionService`   | Service layer logic                     |
| **Integration Tests**     | Complete API workflow                   |

### Running Tests

```bash
# Run all tests
mvn test -s .mvn/settings.xml

# Run specific test class
mvn test -s .mvn/settings.xml -Dtest=BritishTimeConverterTest

# Run tests with coverage
mvn clean test jacoco:report -s .mvn/settings.xml
```

### Viewing Test Results

Test results are available in:
- Console output
- `target/surefire-reports/` (test reports)
- `target/site/jacoco/` (coverage reports)

---

## Code Quality

### Checkstyle

The project uses Checkstyle to enforce coding standards.

```bash
# Run Checkstyle validation
mvn checkstyle:check

# Generate Checkstyle report
mvn checkstyle:checkstyle
```

View the report at `target/site/checkstyle.html`.

### Code Coverage

The project maintains 80%+ code coverage using Jacoco.

```bash
# Generate coverage report
mvn jacoco:report

# Run coverage check
mvn jacoco:check
```

Open `target/site/jacoco/index.html` in a browser to view the coverage report.

---

## CI/CD Pipeline

The project includes a comprehensive GitHub Actions workflow (`.github/workflows/ci.yml`) that automatically:

1. ✅ **Builds** the project with Maven
2. ✅ **Validates** code quality with Checkstyle
3. ✅ **Executes** all tests (193 tests)
4. ✅ **Generates** coverage reports with Jacoco
5. ✅ **Uploads** results to Codecov
6. ✅ **Archives** test results and coverage reports

### Workflow Triggers

- Push to `main` branch
- Pull requests to `main` branch
- Manual workflow dispatch

### Viewing Results

- CI/CD status: Check the badge at the top of this README
- Coverage reports: Available on [Codecov](https://codecov.io/gh/rajeshdasi/british-spoken-time)
- Workflow runs: Available in the [Actions tab](https://github.com/rajeshdasi/british-spoken-time/actions)

---
