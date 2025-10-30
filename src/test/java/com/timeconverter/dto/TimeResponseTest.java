package com.timeconverter.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeResponseTest {

    @Test
    void testNoArgsConstructor() {
        // When
        TimeResponse response = new TimeResponse();

        // Then
        assertNotNull(response);
        assertNull(response.getSpokenTime());
        assertNull(response.getLocale());
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        String spokenTime = "quarter past three";
        String locale = "en-GB";

        // When
        TimeResponse response = new TimeResponse(spokenTime, locale);

        // Then
        assertNotNull(response);
        assertEquals(spokenTime, response.getSpokenTime());
        assertEquals(locale, response.getLocale());
    }

    @Test
    void testSettersAndGetters() {
        // Given
        TimeResponse response = new TimeResponse();
        String spokenTime = "half past seven";
        String locale = "de-DE";

        // When
        response.setSpokenTime(spokenTime);
        response.setLocale(locale);

        // Then
        assertEquals(spokenTime, response.getSpokenTime());
        assertEquals(locale, response.getLocale());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        TimeResponse response1 = new TimeResponse("quarter past three", "en-GB");
        TimeResponse response2 = new TimeResponse("quarter past three", "en-GB");
        TimeResponse response3 = new TimeResponse("half past seven", "de-DE");

        // Then
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        // Given
        TimeResponse response = new TimeResponse("quarter past three", "en-GB");

        // When
        String result = response.toString();

        // Then
        assertNotNull(result);
        assertTrue(result.contains("quarter past three"));
        assertTrue(result.contains("en-GB"));
    }

    @Test
    void testWithNullValues() {
        // When
        TimeResponse response = new TimeResponse(null, null);

        // Then
        assertNotNull(response);
        assertNull(response.getSpokenTime());
        assertNull(response.getLocale());
    }
}
