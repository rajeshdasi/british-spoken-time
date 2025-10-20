# Quick Start Guide

Get the British Spoken Time API running in under 5 minutes!

## Prerequisites

- Java 21 or higher
- Maven 3.8+

Check your versions:
```bash
java -version   # Should show Java 21
mvn -version    # Should show Maven 3.8+
```

## Setup

### 1. Navigate to project
```bash
cd /Users/rajeshsamson/CascadeProjects/british-spoken-time
```

### 2. Build the project
```bash
mvn clean install
```

Expected output: `BUILD SUCCESS` with `132 tests passed`

### 3. Start the application
```bash
mvn spring-boot:run
```

Wait for: `Started BritishSpokenTimeApplication in X seconds`

### 4. Test the API

Open a new terminal and try these commands:

```bash
# Test 1: Noon
curl "http://localhost:8080/api/time/convert?time=12:00"
# Expected: {"spokenTime":"noon","locale":"en_GB"}

# Test 2: Half past seven
curl "http://localhost:8080/api/time/convert?time=7:30"
# Expected: {"spokenTime":"half past seven","locale":"en_GB"}

# Test 3: Quarter to ten
curl "http://localhost:8080/api/time/convert?time=9:45"
# Expected: {"spokenTime":"quarter to ten","locale":"en_GB"}

# Test 4: Midnight
curl "http://localhost:8080/api/time/convert?time=0:00"
# Expected: {"spokenTime":"midnight","locale":"en_GB"}
```

## Common Commands

### Run tests only
```bash
mvn test
```

### Check code quality
```bash
mvn checkstyle:check
```

### Generate coverage report
```bash
mvn jacoco:report
# Open: target/site/jacoco/index.html
```

### Build without tests (faster)
```bash
mvn clean install -DskipTests
```

### Package as JAR
```bash
mvn package
java -jar target/british-spoken-time-1.0.0.jar
```

## API Endpoints

### Main Endpoint
**GET** `/api/time/convert`

**Parameters:**
- `time` (required): Time in HH:mm format (e.g., "12:00", "7:30")
- `locale` (optional): Locale code (default: "en_GB")

**Success Response:**
```json
{
  "spokenTime": "noon",
  "locale": "en_GB"
}
```

**Error Response:**
```json
{
  "error": "Invalid time format: 25:00"
}
```

## Troubleshooting

### Port 8080 already in use
Change the port in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Tests failing
Ensure you're using Java 21:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

### Maven dependency issues
Clear local repository cache:
```bash
rm -rf ~/.m2/repository/com/timeconverter
mvn clean install
```

## Next Steps

1. **Read the full documentation**: See `README.md`
2. **Explore API examples**: See `API_EXAMPLES.md`
3. **Add new locales**: See `CONTRIBUTING.md`
4. **View test coverage**: Run `mvn jacoco:report` and open `target/site/jacoco/index.html`

## Support

- Check `README.md` for detailed documentation
- Review `PROJECT_SUMMARY.md` for architecture overview
- See `API_EXAMPLES.md` for more usage examples

**You're all set! 🚀**
