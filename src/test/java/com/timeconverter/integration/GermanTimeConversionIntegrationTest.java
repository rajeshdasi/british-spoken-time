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
class GermanTimeConversionIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
            "0:00, de_DE, Mitternacht",
            "12:00, de_DE, Mittag",
            "1:00, de_DE, eins Uhr",
            "2:05, de_DE, fünf Minuten nach zwei",
            "3:10, de_DE, zehn Minuten nach drei",
            "3:15, de_DE, Viertel nach drei",
            "5:20, de_DE, zwanzig Minuten nach fünf",
            "9:30, de_DE, halb zehn",
            "8:40, de_DE, zwanzig Minuten vor neun",
            "9:45, de_DE, Viertel vor zehn",
            "10:50, de_DE, zehn Minuten vor elf",
            "11:55, de_DE, fünf Minuten vor zwölf"
    })
    void convertTime_shouldReturnCorrectGermanSpokenTime(String time, String locale, String expectedSpoken) {
        String url = "/api/time/convert?time=" + time + "&locale=" + locale;
        
        ResponseEntity<TimeResponse> response = restTemplate.getForEntity(url, TimeResponse.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedSpoken, response.getBody().getSpokenTime());
        assertEquals(locale, response.getBody().getLocale());
    }

    @Test
    void convertTime_shouldHandleGermanLocaleParameter() {
        String url = "/api/time/convert?time=9:30&locale=de_DE";
        
        ResponseEntity<TimeResponse> response = restTemplate.getForEntity(url, TimeResponse.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("halb zehn", response.getBody().getSpokenTime());
        assertEquals("de_DE", response.getBody().getLocale());
    }
}
