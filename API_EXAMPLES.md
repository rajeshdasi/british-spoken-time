# API Examples

## Basic Usage

### Convert a time (using default locale)

```bash
curl "http://localhost:8080/api/time/convert?time=12:00"
```

Response:
```json
{
  "spokenTime": "noon",
  "locale": "en_GB"
}
```

### Convert with explicit locale

```bash
curl "http://localhost:8080/api/time/convert?time=7:30&locale=en_GB"
```

Response:
```json
{
  "spokenTime": "half past seven",
  "locale": "en_GB"
}
```

## All British English Examples

### Morning Times

```bash
# 1:00 AM
curl "http://localhost:8080/api/time/convert?time=1:00"
# Response: {"spokenTime":"one o'clock","locale":"en_GB"}

# 2:05 AM
curl "http://localhost:8080/api/time/convert?time=2:05"
# Response: {"spokenTime":"five past two","locale":"en_GB"}

# 3:10 AM
curl "http://localhost:8080/api/time/convert?time=3:10"
# Response: {"spokenTime":"ten past three","locale":"en_GB"}

# 4:15 AM
curl "http://localhost:8080/api/time/convert?time=4:15"
# Response: {"spokenTime":"quarter past four","locale":"en_GB"}

# 5:20 AM
curl "http://localhost:8080/api/time/convert?time=5:20"
# Response: {"spokenTime":"twenty past five","locale":"en_GB"}

# 6:25 AM
curl "http://localhost:8080/api/time/convert?time=6:25"
# Response: {"spokenTime":"twenty five past six","locale":"en_GB"}

# 6:32 AM
curl "http://localhost:8080/api/time/convert?time=6:32"
# Response: {"spokenTime":"six thirty two","locale":"en_GB"}

# 7:30 AM
curl "http://localhost:8080/api/time/convert?time=7:30"
# Response: {"spokenTime":"half past seven","locale":"en_GB"}

# 7:35 AM
curl "http://localhost:8080/api/time/convert?time=7:35"
# Response: {"spokenTime":"twenty five to eight","locale":"en_GB"}

# 8:40 AM
curl "http://localhost:8080/api/time/convert?time=8:40"
# Response: {"spokenTime":"twenty to nine","locale":"en_GB"}

# 9:45 AM
curl "http://localhost:8080/api/time/convert?time=9:45"
# Response: {"spokenTime":"quarter to ten","locale":"en_GB"}

# 10:50 AM
curl "http://localhost:8080/api/time/convert?time=10:50"
# Response: {"spokenTime":"ten to eleven","locale":"en_GB"}

# 11:55 AM
curl "http://localhost:8080/api/time/convert?time=11:55"
# Response: {"spokenTime":"five to twelve","locale":"en_GB"}
```

### Special Times

```bash
# Midnight
curl "http://localhost:8080/api/time/convert?time=0:00"
# Response: {"spokenTime":"midnight","locale":"en_GB"}

# Noon
curl "http://localhost:8080/api/time/convert?time=12:00"
# Response: {"spokenTime":"noon","locale":"en_GB"}
```

### Afternoon/Evening Times

```bash
# 1:00 PM
curl "http://localhost:8080/api/time/convert?time=13:00"
# Response: {"spokenTime":"one o'clock","locale":"en_GB"}

# 3:15 PM
curl "http://localhost:8080/api/time/convert?time=15:15"
# Response: {"spokenTime":"quarter past three","locale":"en_GB"}

# 6:30 PM
curl "http://localhost:8080/api/time/convert?time=18:30"
# Response: {"spokenTime":"half past six","locale":"en_GB"}

# 9:45 PM
curl "http://localhost:8080/api/time/convert?time=21:45"
# Response: {"spokenTime":"quarter to ten","locale":"en_GB"}

# 11:55 PM
curl "http://localhost:8080/api/time/convert?time=23:55"
# Response: {"spokenTime":"five to twelve","locale":"en_GB"}
```

## Error Handling

### Invalid time format

```bash
curl "http://localhost:8080/api/time/convert?time=invalid"
```

Response (400 Bad Request):
```json
{
  "error": "Invalid time format: invalid"
}
```

### Hour out of range

```bash
curl "http://localhost:8080/api/time/convert?time=25:00"
```

Response (400 Bad Request):
```json
{
  "error": "Invalid time format: 25:00"
}
```

### Minute out of range

```bash
curl "http://localhost:8080/api/time/convert?time=12:60"
```

Response (400 Bad Request):
```json
{
  "error": "Invalid time format: 12:60"
}
```

### Unsupported locale

```bash
curl "http://localhost:8080/api/time/convert?time=12:00&locale=fr_FR"
```

Response (400 Bad Request):
```json
{
  "error": "Unsupported locale: fr_FR"
}
```

## Using with HTTPie

If you prefer HTTPie:

```bash
# Basic request
http GET localhost:8080/api/time/convert time==12:00

# With locale
http GET localhost:8080/api/time/convert time==7:30 locale==en_GB
```

## Using with Postman

1. Method: GET
2. URL: `http://localhost:8080/api/time/convert`
3. Query Parameters:
   - Key: `time`, Value: `12:00`
   - Key: `locale` (optional), Value: `en_GB`

## Using with JavaScript/Fetch

```javascript
fetch('http://localhost:8080/api/time/convert?time=12:00')
  .then(response => response.json())
  .then(data => console.log(data.spokenTime));
```

## Using with Python

```python
import requests

response = requests.get('http://localhost:8080/api/time/convert', 
                       params={'time': '12:00', 'locale': 'en_GB'})
data = response.json()
print(data['spokenTime'])  # Output: noon
```
