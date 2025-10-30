package com.timeconverter.formatter.german;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GermanNumberConverterTest {

    private GermanNumberConverter converter;

    @BeforeEach
    void setUp() {
        converter = new GermanNumberConverter();
    }

    @ParameterizedTest
    @CsvSource({
        "0, zwölf",
        "1, eins",
        "2, zwei",
        "3, drei",
        "4, vier",
        "5, fünf",
        "6, sechs",
        "7, sieben",
        "8, acht",
        "9, neun",
        "10, zehn",
        "11, elf",
        "12, zwölf",
        "13, eins",
        "14, zwei",
        "23, elf"
    })
    void testConvertHourToWord(int hour, String expected) {
        assertEquals(expected, converter.convertHourToWord(hour));
    }

    @ParameterizedTest
    @CsvSource({
        "1, eins",
        "2, zwei",
        "3, drei",
        "4, vier",
        "5, fünf",
        "6, sechs",
        "7, sieben",
        "8, acht",
        "9, neun",
        "10, zehn",
        "11, elf",
        "12, zwölf",
        "13, dreizehn",
        "14, vierzehn",
        "15, fünfzehn",
        "16, sechzehn",
        "17, siebzehn",
        "18, achtzehn",
        "19, neunzehn",
        "20, zwanzig",
        "21, einundzwanzig",
        "22, zweiundzwanzig",
        "23, dreiundzwanzig",
        "24, vierundzwanzig",
        "25, fünfundzwanzig",
        "26, sechsundzwanzig",
        "27, siebenundzwanzig",
        "28, achtundzwanzig",
        "29, neunundzwanzig",
        "30, dreißig"
    })
    void testConvertNumberToWord(int number, String expected) {
        assertEquals(expected, converter.convertNumberToWord(number));
    }

    @Test
    void testConvertNumberToWord_InvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(0));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(31));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(-1));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(60));
    }
}
