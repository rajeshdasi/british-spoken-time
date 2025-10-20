package com.timeconverter.formatter.german;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GermanOClockHandlerTest {

    private GermanOClockHandler handler;
    private GermanNumberConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new GermanNumberConverter();
        handler = new GermanOClockHandler(numberConverter);
    }

    @Test
    void canHandle_shouldReturnTrueForZeroMinutes() {
        assertTrue(handler.canHandle(0));
    }

    @Test
    void canHandle_shouldReturnFalseForNonZeroMinutes() {
        assertFalse(handler.canHandle(15));
        assertFalse(handler.canHandle(30));
        assertFalse(handler.canHandle(45));
    }

    @Test
    void format_shouldReturnMitternachtForMidnight() {
        TimeInput time = new TimeInput(0, 0);
        assertEquals("Mitternacht", handler.format(time));
    }

    @Test
    void format_shouldReturnMittagForNoon() {
        TimeInput time = new TimeInput(12, 0);
        assertEquals("Mittag", handler.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, eins Uhr",
            "2, 0, zwei Uhr",
            "3, 0, drei Uhr",
            "9, 0, neun Uhr",
            "13, 0, eins Uhr",
            "20, 0, acht Uhr"
    })
    void format_shouldReturnCorrectGermanOClockFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
