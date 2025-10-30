package com.timeconverter.controller;

import com.timeconverter.dto.TimeResponse;
import com.timeconverter.service.TimeConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeConversionControllerTest {

    @Mock
    private TimeConversionService conversionService;

    private TimeConversionController controller;

    @BeforeEach
    void setUp() {
        controller = new TimeConversionController(conversionService);
    }

    @Test
    void testConvertTime_WithLocale() {
        // Given
        String time = "15:15";
        String locale = "en-GB";
        TimeResponse expectedResponse = new TimeResponse("quarter past three", locale);

        when(conversionService.convertTime(time, locale))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, locale);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(time, locale);
    }

    @Test
    void testConvertTime_WithoutLocale() {
        // Given
        String time = "12:00";
        TimeResponse expectedResponse = new TimeResponse("twelve o'clock", "en-GB");

        when(conversionService.convertTime(eq(time), isNull()))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, null);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(eq(time), isNull());
    }

    @Test
    void testConvertTime_GermanLocale() {
        // Given
        String time = "14:30";
        String locale = "de-DE";
        TimeResponse expectedResponse = new TimeResponse("halb drei", locale);

        when(conversionService.convertTime(time, locale))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, locale);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(time, locale);
    }

    @Test
    void testConvertTime_Midnight() {
        // Given
        String time = "00:00";
        String locale = "en-GB";
        TimeResponse expectedResponse = new TimeResponse("midnight", locale);

        when(conversionService.convertTime(time, locale))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, locale);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(time, locale);
    }

    @Test
    void testConvertTime_Noon() {
        // Given
        String time = "12:00";
        String locale = "en-GB";
        TimeResponse expectedResponse = new TimeResponse("twelve o'clock", locale);

        when(conversionService.convertTime(time, locale))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, locale);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(time, locale);
    }

    @Test
    void testConvertTime_VariousFormats() {
        // Given
        String time = "9:45";
        String locale = "en-GB";
        TimeResponse expectedResponse = new TimeResponse("quarter to ten", locale);

        when(conversionService.convertTime(time, locale))
                .thenReturn(Mono.just(expectedResponse));

        // When
        Mono<TimeResponse> result = controller.convertTime(time, locale);

        // Then
        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(conversionService).convertTime(time, locale);
    }
}
