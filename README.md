# British Spoken Time Converter

[![CI/CD Pipeline](https://github.com/rajeshdasi/british-spoken-time/actions/workflows/ci.yml/badge.svg)](https://github.com/rajeshdasi/british-spoken-time/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/rajeshdasi/british-spoken-time/branch/main/graph/badge.svg)](https://codecov.io/gh/rajeshdasi/british-spoken-time)

A Spring Boot REST API that converts digital time format (HH:mm) to spoken time format in multiple languages.

## Features

- ✅ Convert digital time to spoken English (British) and German
- ✅ RESTful API with Spring Boot
- ✅ Extensible architecture supporting multiple locales
- ✅ Comprehensive unit and integration tests (193 tests)
- ✅ Code coverage with Jacoco (80%+ coverage)
- ✅ Code quality checks with Checkstyle
- ✅ CI/CD pipeline with GitHub Actions
- ✅ Built with Java 21 LTS
- ✅ Spring-managed components with dependency injection

## Conversion Examples

### British English (en_GB)

| Input   | Output                  |
|---------|-------------------------|
| 1:00    | one o'clock            |
| 2:05    | five past two          |
| 3:10    | ten past three         |
| 4:15    | quarter past four      |
| 5:20    | twenty past five       |
| 6:25    | twenty five past six   |
| 6:32    | six thirty two         |
| 7:30    | half past seven        |
| 7:35    | twenty five to eight   |
| 8:40    | twenty to nine         |
| 9:45    | quarter to ten         |
| 10:50   | ten to eleven          |
| 11:55   | five to twelve         |
| 0:00    | midnight               |
| 12:00   | noon                   |

### German (de_DE)

| Input   | Output                      |
|---------|-----------------------------|
| 1:00    | eins Uhr                    |
| 2:05    | fünf Minuten nach zwei      |
| 3:10    | zehn Minuten nach drei      |
| 3:15    | Viertel nach drei           |
| 5:20    | zwanzig Minuten nach fünf   |
| 9:30    | halb zehn                   |
| 8:40    | zwanzig Minuten vor neun    |
| 9:45    | Viertel vor zehn            |
| 10:50   | zehn Minuten vor elf        |
| 11:55   | fünf Minuten vor zwölf      |
| 0:00    | Mitternacht                 |
| 12:00   | Mittag                      |

**Note**: In German, "halb zehn" (half ten) means 9:30 - half to ten, not half past nine.

## Technical Stack

- **Java**: 21 (LTS)
- **Framework**: Spring Boot 3.2.0
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test
- **Code Coverage**: Jacoco
- **Code Quality**: Checkstyle
- **CI/CD**: GitHub Actions

## Prerequisites

- JDK 21 or higher
- Maven 3.8 or higher

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/rajeshdasi/british-spoken-time.git
cd british-spoken-time
```

### Build the Project

**Important**: The project includes a custom Maven settings file (`.mvn/settings.xml`) to ensure correct dependency resolution. Always include `-s .mvn/settings.xml` in your Maven commands:

```bash
mvn clean install -s .mvn/settings.xml
```

### Run Tests

```bash
mvn test -s .mvn/settings.xml
```

### Run the Application

```bash
java -jar target/british-spoken-time-1.0.0.jar
```

Alternatively, using Maven:
```bash
mvn spring-boot:run -s .mvn/settings.xml
```

The application will start on `http://localhost:8080`.

## API Usage

### Convert Time

**Endpoint**: `GET /api/time/convert`

**Parameters**:
- `time` (required): Time in HH:mm or H:mm format
- `locale` (optional): Locale for conversion
  - `en_GB` - British English (default)
  - `de_DE` - German

**Example Requests**:

**British English**:
```bash
curl "http://localhost:8080/api/time/convert?time=12:00"
# or explicitly
curl "http://localhost:8080/api/time/convert?time=12:00&locale=en_GB"
```

**Example Response**:
```json
{
  "spokenTime": "noon",
  "locale": "en_GB"
}
```

**German**:
```bash
curl "http://localhost:8080/api/time/convert?time=9:30&locale=de_DE"
```

**Example Response**:
```json
{
  "spokenTime": "halb zehn",
  "locale": "de_DE"
}
```

**Error Response**:
```json
{
  "error": "Invalid time format: 25:00"
}
```

## Code Quality

### Running Checkstyle

```bash
mvn checkstyle:check
```

### Generating Coverage Report

```bash
mvn jacoco:report
```

Open `target/site/jacoco/index.html` in a browser to view the coverage report.

## CI/CD Pipeline

The project includes a GitHub Actions workflow that:

1. ✅ Builds the project
2. ✅ Runs Checkstyle validation
3. ✅ Executes all tests
4. ✅ Generates coverage reports
5. ✅ Uploads results to Codecov
6. ✅ Archives test results and coverage reports

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

## Testing

The project includes comprehensive tests:

- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test the entire API workflow
- **Parameterized Tests**: Extensive test coverage using JUnit 5's `@ParameterizedTest`

### Test Coverage

- `TimeInput`: Input parsing and validation
- `OClockHandler`: Zero-minute cases (midnight, noon, o'clock)
- `PastMinuteHandler`: Minutes 1-30
- `ToMinuteHandler`: Minutes 35, 40, 45, 50, 55
- `DefaultMinuteHandler`: Non-standard minute values
- `BritishTimeConverter`: End-to-end conversion logic
- `TimeConverterFactory`: Locale selection
- `TimeConversionService`: Service layer logic
- **Integration Tests**: Complete API workflow
