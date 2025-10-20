package com.timeconverter.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeInputTest {

    @ParameterizedTest
    @CsvSource({
            "1:00, 1, 0",
            "12:00, 12, 0",
            "23:59, 23, 59",
            "0:00, 0, 0",
            "6:32, 6, 32"
    })
    void parse_shouldParseValidTimeFormats(String time, int expectedHour, int expectedMinute) {
        TimeInput result = TimeInput.parse(time);
        
        assertEquals(expectedHour, result.getHour());
        assertEquals(expectedMinute, result.getMinute());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "25:00",
            "12:60",
            "-1:00",
            "12:-5",
            "invalid",
            "12",
            "12:",
            ":30",
            ""
    })
    void parse_shouldThrowExceptionForInvalidFormats(String time) {
        assertThrows(IllegalArgumentException.class, () -> TimeInput.parse(time));
    }

    @Test
    void parse_shouldThrowExceptionForNullInput() {
        assertThrows(IllegalArgumentException.class, () -> TimeInput.parse(null));
    }
}
