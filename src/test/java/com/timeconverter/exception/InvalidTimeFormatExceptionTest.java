package com.timeconverter.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidTimeFormatExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Invalid time format: 25:00";

        // When
        InvalidTimeFormatException exception = new InvalidTimeFormatException(errorMessage);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // Given
        String errorMessage = "Invalid time format";
        Throwable cause = new IllegalArgumentException("Hours must be between 0 and 23");

        // When
        InvalidTimeFormatException exception = new InvalidTimeFormatException(errorMessage, cause);

        // Then
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("Hours must be between 0 and 23", exception.getCause().getMessage());
    }

    @Test
    void testExceptionCanBeThrown() {
        // Given
        String errorMessage = "Time format is invalid";

        // When & Then
        InvalidTimeFormatException exception = assertThrows(
                InvalidTimeFormatException.class,
                () -> {
                    throw new InvalidTimeFormatException(errorMessage);
                }
        );

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithCauseCanBeThrown() {
        // Given
        String errorMessage = "Failed to parse time";
        NumberFormatException cause = new NumberFormatException("For input string: \"abc\"");

        // When & Then
        InvalidTimeFormatException exception = assertThrows(
                InvalidTimeFormatException.class,
                () -> {
                    throw new InvalidTimeFormatException(errorMessage, cause);
                }
        );

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testExceptionIsRuntimeException() {
        // Given
        InvalidTimeFormatException exception = new InvalidTimeFormatException("test");

        // Then
        assertTrue(exception instanceof RuntimeException);
    }
}
