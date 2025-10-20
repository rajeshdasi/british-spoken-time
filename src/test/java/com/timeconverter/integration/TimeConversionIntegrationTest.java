package com.timeconverter.integration;

import com.timeconverter.model.TimeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeConversionIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
            "1:00, one o'clock",
            "2:05, five past two",
            "3:10, ten past three",
            "4:15, quarter past four",
            "5:20, twenty past five",
            "6:25, twenty five past six",
            "6:32, six thirty two",
            "7:30, half past seven",
            "7:35, twenty five to eight",
            "8:40, twenty to nine",
            "9:45, quarter to ten",
            "10:50, ten to eleven",
            "11:55, five to twelve",
            "0:00, midnight",
            "12:00, noon"
    })
    void convertTime_shouldReturnCorrectBritishSpokenTime(String time, String expectedSpoken) {
        String url = "/api/time/convert?time=" + time;
        
        ResponseEntity<TimeResponse> response = restTemplate.getForEntity(url, TimeResponse.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedSpoken, response.getBody().getSpokenTime());
        assertEquals("en_GB", response.getBody().getLocale());
    }

    @Test
    void convertTime_shouldReturnBadRequestForInvalidTime() {
        String url = "/api/time/convert?time=invalid";
        
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void convertTime_shouldHandleLocaleParameter() {
        String url = "/api/time/convert?time=12:00&locale=en_GB";
        
        ResponseEntity<TimeResponse> response = restTemplate.getForEntity(url, TimeResponse.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("noon", response.getBody().getSpokenTime());
        assertEquals("en_GB", response.getBody().getLocale());
    }
}
