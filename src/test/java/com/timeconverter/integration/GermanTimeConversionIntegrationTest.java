package com.timeconverter.integration;

import com.timeconverter.model.TimeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GermanTimeConversionIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

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
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/time/convert")
                        .queryParam("time", time)
                        .queryParam("locale", locale)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeResponse.class)
                .value(response -> {
                    assert response.getSpokenTime().equals(expectedSpoken);
                    assert response.getLocale().equals(locale);
                });
    }

    @Test
    void convertTime_shouldHandleGermanLocaleParameter() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/time/convert")
                        .queryParam("time", "9:30")
                        .queryParam("locale", "de_DE")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeResponse.class)
                .value(response -> {
                    assert response.getSpokenTime().equals("halb zehn");
                    assert response.getLocale().equals("de_DE");
                });
    }
}
