package com.timeconverter.formatter.german;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GermanMinutesHandlerTest {

    private GermanMinutesHandler handler;
    private GermanNumberConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new GermanNumberConverter();
        handler = new GermanMinutesHandler(numberConverter);
    }

    @Test
    void canHandle_shouldReturnTrueForMinutesExceptSpecialCases() {
        assertTrue(handler.canHandle(5));
        assertTrue(handler.canHandle(10));
        assertTrue(handler.canHandle(20));
        assertTrue(handler.canHandle(40));
        assertTrue(handler.canHandle(50));
    }

    @Test
    void canHandle_shouldReturnFalseForSpecialCases() {
        assertFalse(handler.canHandle(0));
        assertFalse(handler.canHandle(15));
        assertFalse(handler.canHandle(30));
        assertFalse(handler.canHandle(45));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 5, fünf Minuten nach zwei",
            "3, 10, zehn Minuten nach drei",
            "5, 20, zwanzig Minuten nach fünf",
            "8, 40, zwanzig Minuten vor neun",
            "9, 50, zehn Minuten vor zehn",
            "11, 55, fünf Minuten vor zwölf"
    })
    void format_shouldReturnCorrectGermanMinutesFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
