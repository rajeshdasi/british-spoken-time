package com.timeconverter.service;

import com.timeconverter.exception.InvalidTimeFormatException;
import com.timeconverter.factory.TimeConverterFactory;
import com.timeconverter.formatter.TimeConverter;
import com.timeconverter.formatter.british.BetweenZeroAndThirtyHandler;
import com.timeconverter.formatter.british.BritishTimeConverter;
import com.timeconverter.formatter.british.DefaultMinuteHandler;
import com.timeconverter.formatter.british.NumberToWordConverter;
import com.timeconverter.formatter.british.OClockHandler;
import com.timeconverter.formatter.british.PastMinuteHandler;
import com.timeconverter.formatter.british.ToMinuteHandler;
import com.timeconverter.model.TimeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeConversionServiceImplTest {

    private TimeConversionService service;

    @BeforeEach
    void setUp() {
        NumberToWordConverter numberConverter = new NumberToWordConverter();
        TimeConverter britishConverter = new BritishTimeConverter(List.of(
                new OClockHandler(numberConverter),
                new PastMinuteHandler(numberConverter),
                new ToMinuteHandler(numberConverter),
                new BetweenZeroAndThirtyHandler(numberConverter),
                new DefaultMinuteHandler(numberConverter)
        ));
        TimeConverterFactory factory = new TimeConverterFactory(List.of(britishConverter), "en_GB");
        service = new TimeConversionServiceImpl(factory);
    }

    @ParameterizedTest
    @CsvSource({
            "1:00, en_GB, one o'clock",
            "2:05, en_GB, five past two",
            "12:00, en_GB, noon",
            "0:00, en_GB, midnight",
            "6:32, en_GB, six thirty two"
    })
    void convertTime_shouldReturnCorrectSpokenTime(String time, String locale, String expectedSpoken) {
        TimeResponse response = service.convertTime(time, locale);
        
        assertNotNull(response);
        assertEquals(expectedSpoken, response.getSpokenTime());
        assertEquals(locale, response.getLocale());
    }

    @Test
    void convertTime_shouldUseDefaultLocaleWhenNotProvided() {
        TimeResponse response = service.convertTime("12:00", null);
        
        assertNotNull(response);
        assertEquals("noon", response.getSpokenTime());
        assertEquals("en_GB", response.getLocale());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "25:00", "12:60", ""})
    void convertTime_shouldThrowExceptionForInvalidTime(String time) {
        assertThrows(InvalidTimeFormatException.class, () -> service.convertTime(time, "en_GB"));
    }
}
