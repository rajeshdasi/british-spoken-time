package com.timeconverter.formatter.german;

import com.timeconverter.dto.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GermanTimeConverterTest {

    private GermanTimeConverter converter;

    @BeforeEach
    void setUp() {
        GermanNumberConverter numberConverter = new GermanNumberConverter();
        converter = new GermanTimeConverter(List.of(
                new GermanOClockHandler(numberConverter),
                new GermanHalfHandler(numberConverter),
                new GermanQuarterHandler(numberConverter),
                new GermanMinutesHandler(numberConverter)
        ));
    }

    @Test
    void getSupportedLocale_shouldReturnDeDE() {
        assertEquals("de_DE", converter.getSupportedLocale());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, Mitternacht",
            "12, 0, Mittag",
            "1, 0, eins Uhr",
            "2, 5, fünf Minuten nach zwei",
            "3, 10, zehn Minuten nach drei",
            "3, 15, Viertel nach drei",
            "5, 20, zwanzig Minuten nach fünf",
            "9, 30, halb zehn",
            "8, 40, zwanzig Minuten vor neun",
            "9, 45, Viertel vor zehn",
            "10, 50, zehn Minuten vor elf",
            "11, 55, fünf Minuten vor zwölf"
    })
    void convert_shouldReturnCorrectGermanSpokenTime(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        String result = converter.convert(time);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "13, 0, eins Uhr",
            "14, 15, Viertel nach zwei",
            "21, 30, halb zehn",
            "23, 45, Viertel vor zwölf"
    })
    void convert_shouldHandlePMTimeCorrectly(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        String result = converter.convert(time);
        assertEquals(expected, result);
    }
}
