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
class TimeConversionIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

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
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/time/convert")
                        .queryParam("time", time)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeResponse.class)
                .value(response -> {
                    assert response.getSpokenTime().equals(expectedSpoken);
                    assert response.getLocale().equals("en_GB");
                });
    }

    @Test
    void convertTime_shouldReturnBadRequestForInvalidTime() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/time/convert")
                        .queryParam("time", "invalid")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void convertTime_shouldHandleLocaleParameter() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/time/convert")
                        .queryParam("time", "12:00")
                        .queryParam("locale", "en_GB")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeResponse.class)
                .value(response -> {
                    assert response.getSpokenTime().equals("noon");
                    assert response.getLocale().equals("en_GB");
                });
    }
}
