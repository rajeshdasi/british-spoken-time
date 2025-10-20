package com.timeconverter.exception;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();

        // Set up log appender to capture log messages
        Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    void testHandleInvalidTimeFormat() {
        // Given
        String errorMessage = "Invalid time format: 25:00";
        InvalidTimeFormatException exception = new InvalidTimeFormatException(errorMessage);

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleInvalidTimeFormat(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertNotNull(errorMap);
                    assertEquals(1, errorMap.size());
                    assertEquals(errorMessage, errorMap.get("error"));
                })
                .verifyComplete();

        // Verify logging
        List<ILoggingEvent> logsList = listAppender.list;
        assertFalse(logsList.isEmpty());
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getFormattedMessage().contains("Invalid time format"));
    }

    @Test
    void testHandleInvalidTimeFormatWithDifferentMessage() {
        // Given
        String errorMessage = "Time cannot be null";
        InvalidTimeFormatException exception = new InvalidTimeFormatException(errorMessage);

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleInvalidTimeFormat(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertEquals(errorMessage, errorMap.get("error"));
                })
                .verifyComplete();
    }

    @Test
    void testHandleIllegalArgument() {
        // Given
        String errorMessage = "Language cannot be empty";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleIllegalArgument(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertNotNull(errorMap);
                    assertEquals(1, errorMap.size());
                    assertEquals(errorMessage, errorMap.get("error"));
                })
                .verifyComplete();

        // Verify logging
        List<ILoggingEvent> logsList = listAppender.list;
        assertFalse(logsList.isEmpty());
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getFormattedMessage().contains("Invalid argument"));
    }

    @Test
    void testHandleIllegalArgumentWithDifferentMessage() {
        // Given
        String errorMessage = "Invalid parameter value";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleIllegalArgument(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertEquals(errorMessage, errorMap.get("error"));
                })
                .verifyComplete();
    }

    @Test
    void testHandleGenericException() {
        // Given
        Exception exception = new RuntimeException("Unexpected error");

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleGenericException(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertNotNull(errorMap);
                    assertEquals(1, errorMap.size());
                    assertEquals("An unexpected error occurred. Please contact support.", errorMap.get("error"));
                })
                .verifyComplete();

        // Verify logging
        List<ILoggingEvent> logsList = listAppender.list;
        assertFalse(logsList.isEmpty());
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getFormattedMessage().contains("Unexpected error occurred"));
    }

    @Test
    void testHandleGenericExceptionWithNullPointerException() {
        // Given
        Exception exception = new NullPointerException("Null value encountered");

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleGenericException(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertEquals("An unexpected error occurred. Please contact support.", errorMap.get("error"));
                })
                .verifyComplete();
    }

    @Test
    void testHandleGenericExceptionWithIOException() {
        // Given
        Exception exception = new java.io.IOException("File not found");

        // When
        Mono<Map<String, String>> result = globalExceptionHandler.handleGenericException(exception);

        // Then
        StepVerifier.create(result)
                .assertNext(errorMap -> {
                    assertEquals("An unexpected error occurred. Please contact support.", errorMap.get("error"));
                })
                .verifyComplete();
    }

    @Test
    void testAllHandlersReturnMono() {
        // Test that all handlers return Mono types
        InvalidTimeFormatException invalidTimeException = new InvalidTimeFormatException("test");
        IllegalArgumentException illegalArgException = new IllegalArgumentException("test");
        Exception genericException = new Exception("test");

        Mono<Map<String, String>> result1 = globalExceptionHandler.handleInvalidTimeFormat(invalidTimeException);
        Mono<Map<String, String>> result2 = globalExceptionHandler.handleIllegalArgument(illegalArgException);
        Mono<Map<String, String>> result3 = globalExceptionHandler.handleGenericException(genericException);

        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertTrue(result1 instanceof Mono);
        assertTrue(result2 instanceof Mono);
        assertTrue(result3 instanceof Mono);
    }
}
