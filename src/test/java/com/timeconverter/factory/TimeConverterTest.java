package com.timeconverter.factory;

import com.timeconverter.formatter.british.BetweenZeroAndThirtyHandler;
import com.timeconverter.formatter.british.BritishTimeConverter;
import com.timeconverter.formatter.british.DefaultMinuteHandler;
import com.timeconverter.formatter.british.NumberToWordConverter;
import com.timeconverter.formatter.british.OClockHandler;
import com.timeconverter.formatter.british.PastMinuteHandler;
import com.timeconverter.formatter.british.ToMinuteHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeConverterTest {

    private TimeConverter factory;

    @BeforeEach
    void setUp() {
        NumberToWordConverter numberConverter = new NumberToWordConverter();
        com.timeconverter.formatter.TimeConverter britishConverter = new BritishTimeConverter(List.of(
                new OClockHandler(numberConverter),
                new PastMinuteHandler(numberConverter),
                new ToMinuteHandler(numberConverter),
                new BetweenZeroAndThirtyHandler(numberConverter),
                new DefaultMinuteHandler(numberConverter)
        ));
        factory = new TimeConverter(List.of(britishConverter), "en_GB");
    }

    @Test
    void getConverter_shouldReturnBritishConverterForEnGB() {
        com.timeconverter.formatter.TimeConverter converter = factory.getConverter("en_GB");
        assertNotNull(converter);
        assertEquals("en_GB", converter.getSupportedLocale());
    }

    @Test
    void getDefaultConverter_shouldReturnBritishConverter() {
        com.timeconverter.formatter.TimeConverter converter = factory.getDefaultConverter();
        assertNotNull(converter);
        assertEquals("en_GB", converter.getSupportedLocale());
    }

    @Test
    void getConverter_shouldReturnDefaultConverterWhenLocaleIsNull() {
        com.timeconverter.formatter.TimeConverter converter = factory.getConverter(null);
        assertNotNull(converter);
        assertEquals("en_GB", converter.getSupportedLocale());
    }

    @Test
    void getConverter_shouldThrowExceptionForUnsupportedLocale() {
        assertThrows(IllegalArgumentException.class, () -> factory.getConverter("de_DE"));
    }
}
