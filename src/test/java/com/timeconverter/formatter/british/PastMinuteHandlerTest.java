package com.timeconverter.formatter.british;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PastMinuteHandlerTest {

    private PastMinuteHandler handler;
    private NumberToWordConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new NumberToWordConverter();
        handler = new PastMinuteHandler(numberConverter);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25, 30})
    void canHandle_shouldReturnTrueForMinutesBetweenOneAndThirty(int minute) {
        assertTrue(handler.canHandle(minute));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 31, 35, 40, 45, 50, 55, 59})
    void canHandle_shouldReturnFalseForMinutesOutsideRange(int minute) {
        assertFalse(handler.canHandle(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 5, five past two",
            "3, 10, ten past three",
            "4, 15, quarter past four",
            "5, 20, twenty past five",
            "6, 25, twenty five past six",
            "7, 30, half past seven",
            "14, 5, five past two",
            "15, 10, ten past three"
    })
    void format_shouldReturnCorrectPastFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
