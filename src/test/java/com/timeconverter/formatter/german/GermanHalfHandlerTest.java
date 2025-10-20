package com.timeconverter.formatter.german;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GermanHalfHandlerTest {

    private GermanHalfHandler handler;
    private GermanNumberConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new GermanNumberConverter();
        handler = new GermanHalfHandler(numberConverter);
    }

    @Test
    void canHandle_shouldReturnTrueForThirtyMinutes() {
        assertTrue(handler.canHandle(30));
    }

    @Test
    void canHandle_shouldReturnFalseForOtherMinutes() {
        assertFalse(handler.canHandle(0));
        assertFalse(handler.canHandle(15));
        assertFalse(handler.canHandle(45));
    }

    @ParameterizedTest
    @CsvSource({
            "9, 30, halb zehn",
            "10, 30, halb elf",
            "13, 30, halb zwei",
            "23, 30, halb zwölf"
    })
    void format_shouldReturnCorrectGermanHalfFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
