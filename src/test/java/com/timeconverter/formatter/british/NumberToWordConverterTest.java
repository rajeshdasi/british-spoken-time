package com.timeconverter.formatter.british;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberToWordConverterTest {

    private NumberToWordConverter converter;

    @BeforeEach
    void setUp() {
        converter = new NumberToWordConverter();
    }

    @ParameterizedTest
    @CsvSource({
        "0, twelve",
        "1, one",
        "2, two",
        "3, three",
        "4, four",
        "5, five",
        "6, six",
        "7, seven",
        "8, eight",
        "9, nine",
        "10, ten",
        "11, eleven",
        "12, twelve",
        "13, one",
        "14, two",
        "23, eleven"
    })
    void testConvertHourToWord(int hour, String expected) {
        assertEquals(expected, converter.convertHourToWord(hour));
    }

    @ParameterizedTest
    @CsvSource({
        "1, one",
        "2, two",
        "3, three",
        "4, four",
        "5, five",
        "6, six",
        "7, seven",
        "8, eight",
        "9, nine",
        "10, ten",
        "11, eleven",
        "12, twelve",
        "13, thirteen",
        "14, fourteen",
        "15, fifteen",
        "16, sixteen",
        "17, seventeen",
        "18, eighteen",
        "19, nineteen",
        "20, twenty",
        "21, twenty one",
        "22, twenty two",
        "23, twenty three",
        "24, twenty four",
        "25, twenty five",
        "26, twenty six",
        "27, twenty seven",
        "28, twenty eight",
        "29, twenty nine",
        "30, thirty",
        "31, thirty one",
        "32, thirty two",
        "33, thirty three",
        "34, thirty four",
        "35, thirty five",
        "36, thirty six",
        "37, thirty seven",
        "38, thirty eight",
        "39, thirty nine",
        "40, forty",
        "41, forty one",
        "42, forty two",
        "43, forty three",
        "44, forty four",
        "45, forty five",
        "46, forty six",
        "47, forty seven",
        "48, forty eight",
        "49, forty nine",
        "50, fifty",
        "51, fifty one",
        "52, fifty two",
        "53, fifty three",
        "54, fifty four",
        "55, fifty five",
        "56, fifty six",
        "57, fifty seven",
        "58, fifty eight",
        "59, fifty nine"
    })
    void testConvertNumberToWord(int number, String expected) {
        assertEquals(expected, converter.convertNumberToWord(number));
    }

    @Test
    void testConvertNumberToWord_InvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(0));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(60));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(-1));
        assertThrows(IllegalArgumentException.class, () -> converter.convertNumberToWord(100));
    }

    @ParameterizedTest
    @CsvSource({
        "1, o one",
        "2, o two",
        "3, o three",
        "4, o four",
        "5, o five",
        "6, o six",
        "7, o seven",
        "8, o eight",
        "9, o nine",
        "10, ten",
        "15, fifteen",
        "20, twenty",
        "30, thirty",
        "45, forty five",
        "59, fifty nine"
    })
    void testConvertMinuteToWord(int minute, String expected) {
        assertEquals(expected, converter.convertMinuteToWord(minute));
    }
}
