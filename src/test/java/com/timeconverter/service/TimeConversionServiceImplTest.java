package com.timeconverter.service;

import com.timeconverter.factory.TimeConverter;
import com.timeconverter.formatter.british.BetweenZeroAndThirtyHandler;
import com.timeconverter.formatter.british.BritishTimeConverter;
import com.timeconverter.formatter.british.DefaultMinuteHandler;
import com.timeconverter.formatter.british.NumberToWordConverter;
import com.timeconverter.formatter.british.OClockHandler;
import com.timeconverter.formatter.british.PastMinuteHandler;
import com.timeconverter.formatter.british.ToMinuteHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.test.StepVerifier;

import java.util.List;

class TimeConversionServiceImplTest {

    private TimeConversionService service;

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
        TimeConverter factory = new TimeConverter(List.of(britishConverter), "en_GB");
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
        StepVerifier.create(service.convertTime(time, locale))
                .expectNextMatches(response ->
                        response.getSpokenTime().equals(expectedSpoken) &&
                        response.getLocale().equals(locale))
                .verifyComplete();
    }

    @Test
    void convertTime_shouldUseDefaultLocaleWhenNotProvided() {
        StepVerifier.create(service.convertTime("12:00", null))
                .expectNextMatches(response ->
                        response.getSpokenTime().equals("noon") &&
                        response.getLocale().equals("en_GB"))
                .verifyComplete();
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "25:00", "12:60", ""})
    void convertTime_shouldThrowExceptionForInvalidTime(String time) {
        StepVerifier.create(service.convertTime(time, "en_GB"))
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}
