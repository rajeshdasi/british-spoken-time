package com.timeconverter.config;

import com.timeconverter.factory.TimeConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TimeConverterConfigTest {

    @Mock
    private com.timeconverter.formatter.TimeConverter converter1;

    @Mock
    private com.timeconverter.formatter.TimeConverter converter2;

    @BeforeEach
    void setUp() {
        lenient().when(converter1.getSupportedLocale()).thenReturn("en_GB");
        lenient().when(converter2.getSupportedLocale()).thenReturn("de_DE");
    }

    @Test
    void testTimeConverterFactory_CreatesFactoryWithConverters() {
        // Given
        TimeConverterConfig config = new TimeConverterConfig();
        ReflectionTestUtils.setField(config, "defaultLocale", "en_GB");
        List<com.timeconverter.formatter.TimeConverter> converters = Arrays.asList(converter1, converter2);

        // When
        TimeConverter factory = config.timeConverterFactory(converters);

        // Then
        assertNotNull(factory);
        assertNotNull(factory.getDefaultConverter());
        assertEquals(converter1, factory.getConverter("en_GB"));
        assertEquals(converter2, factory.getConverter("de_DE"));
    }

    @Test
    void testTimeConverterFactory_WithSingleConverter() {
        // Given
        TimeConverterConfig config = new TimeConverterConfig();
        ReflectionTestUtils.setField(config, "defaultLocale", "en_GB");
        List<com.timeconverter.formatter.TimeConverter> converters = List.of(converter1);

        // When
        TimeConverter factory = config.timeConverterFactory(converters);

        // Then
        assertNotNull(factory);
        assertEquals(converter1, factory.getDefaultConverter());
    }

    @Test
    void testTimeConverterFactory_WithGermanAsDefault() {
        // Given
        TimeConverterConfig config = new TimeConverterConfig();
        ReflectionTestUtils.setField(config, "defaultLocale", "de_DE");
        List<com.timeconverter.formatter.TimeConverter> converters = Arrays.asList(converter1, converter2);

        // When
        TimeConverter factory = config.timeConverterFactory(converters);

        // Then
        assertNotNull(factory);
        assertEquals(converter2, factory.getDefaultConverter());
    }

    @Test
    void testTimeConverterFactory_WithMultipleConverters() {
        // Given
        TimeConverterConfig config = new TimeConverterConfig();
        ReflectionTestUtils.setField(config, "defaultLocale", "en_GB");
        List<com.timeconverter.formatter.TimeConverter> converters = Arrays.asList(converter1, converter2);

        // When
        TimeConverter factory = config.timeConverterFactory(converters);

        // Then
        assertNotNull(factory);
        assertEquals(converter1, factory.getConverter("en_GB"));
        assertEquals(converter2, factory.getConverter("de_DE"));
    }
}
