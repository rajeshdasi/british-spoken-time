package com.timeconverter.formatter.british;

import com.timeconverter.model.TimeInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BritishTimeConverterTest {

    private BritishTimeConverter converter;

    @BeforeEach
    void setUp() {
        NumberToWordConverter numberConverter = new NumberToWordConverter();
        converter = new BritishTimeConverter(List.of(
                new OClockHandler(numberConverter),
                new PastMinuteHandler(numberConverter),
                new ToMinuteHandler(numberConverter),
                new BetweenZeroAndThirtyHandler(numberConverter),
                new DefaultMinuteHandler(numberConverter)
        ));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, one o'clock",
            "2, 5, five past two",
            "3, 10, ten past three",
            "4, 15, quarter past four",
            "5, 20, twenty past five",
            "6, 25, twenty five past six",
            "6, 32, six thirty two",
            "7, 30, half past seven",
            "7, 35, twenty five to eight",
            "8, 40, twenty to nine",
            "9, 45, quarter to ten",
            "10, 50, ten to eleven",
            "11, 55, five to twelve",
            "0, 0, midnight",
            "12, 0, noon"
    })
    void convert_shouldReturnCorrectBritishSpokenTime(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        String result = converter.convert(time);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "13, 0, one o'clock",
            "14, 5, five past two",
            "15, 10, ten past three",
            "23, 55, five to twelve"
    })
    void convert_shouldHandlePMTimeCorrectly(int hour, int minute, String expected) {
        TimeInput time = new TimeInput(hour, minute);
        String result = converter.convert(time);
        assertEquals(expected, result);
    }
}
