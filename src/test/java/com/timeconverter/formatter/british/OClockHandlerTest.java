package com.timeconverter.formatter.british;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OClockHandlerTest {

    private OClockHandler handler;
    private NumberToWordConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new NumberToWordConverter();
        handler = new OClockHandler(numberConverter);
    }

    @Test
    void canHandle_shouldReturnTrueForZeroMinutes() {
        assertTrue(handler.canHandle(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 15, 30, 45, 59})
    void canHandle_shouldReturnFalseForNonZeroMinutes(int minute) {
        assertFalse(handler.canHandle(minute));
    }

    @Test
    void format_shouldReturnMidnightFor0000() {
        TimeInput time = new TimeInput(0, 0);
        assertEquals("midnight", handler.format(time));
    }

    @Test
    void format_shouldReturnNoonFor1200() {
        TimeInput time = new TimeInput(12, 0);
        assertEquals("noon", handler.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, one o'clock",
            "2, 0, two o'clock",
            "3, 0, three o'clock",
            "4, 0, four o'clock",
            "5, 0, five o'clock",
            "6, 0, six o'clock",
            "7, 0, seven o'clock",
            "8, 0, eight o'clock",
            "9, 0, nine o'clock",
            "10, 0, ten o'clock",
            "11, 0, eleven o'clock",
            "13, 0, one o'clock",
            "14, 0, two o'clock",
            "23, 0, eleven o'clock"
    })
    void format_shouldReturnCorrectOClockFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
