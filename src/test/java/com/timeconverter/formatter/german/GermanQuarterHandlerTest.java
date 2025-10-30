package com.timeconverter.formatter.german;

import com.timeconverter.dto.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GermanQuarterHandlerTest {

    private GermanQuarterHandler handler;
    private GermanNumberConverter numberConverter;

    @BeforeEach
    void setUp() {
        numberConverter = new GermanNumberConverter();
        handler = new GermanQuarterHandler(numberConverter);
    }

    @Test
    void canHandle_shouldReturnTrueForFifteenAndFortyFive() {
        assertTrue(handler.canHandle(15));
        assertTrue(handler.canHandle(45));
    }

    @Test
    void canHandle_shouldReturnFalseForOtherMinutes() {
        assertFalse(handler.canHandle(0));
        assertFalse(handler.canHandle(30));
        assertFalse(handler.canHandle(20));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 15, Viertel nach zehn",
            "3, 15, Viertel nach drei",
            "10, 45, Viertel vor elf",
            "9, 45, Viertel vor zehn",
            "23, 45, Viertel vor zwölf"
    })
    void format_shouldReturnCorrectGermanQuarterFormat(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        assertEquals(expected, handler.format(time));
    }
}
