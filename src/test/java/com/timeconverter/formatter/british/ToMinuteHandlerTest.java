package com.timeconverter.formatter.british;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToMinuteHandlerTest {

    private ToMinuteHandler handler;
    private NumberToWordConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new NumberToWordConverter();
        handler = new ToMinuteHandler(numberConverter);
    }

    @ParameterizedTest
    @ValueSource(ints = {35, 40, 45, 50, 55})
    void canHandle_shouldReturnTrueForToMinutes(int minute) {
        assertTrue(handler.canHandle(minute));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10, 15, 20, 25, 30, 32, 36, 59})
    void canHandle_shouldReturnFalseForNonToMinutes(int minute) {
        assertFalse(handler.canHandle(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "7, 35, twenty five to eight",
            "8, 40, twenty to nine",
            "9, 45, quarter to ten",
            "10, 50, ten to eleven",
            "11, 55, five to twelve",
            "23, 55, five to twelve"
    })
    void format_shouldReturnCorrectToFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
