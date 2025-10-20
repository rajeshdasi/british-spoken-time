package com.timeconverter.factory;

import com.timeconverter.formatter.TimeConverter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TimeConverterFactory {

    private final Map<String, TimeConverter> converters;
    private final String defaultLocale;

    public TimeConverterFactory(List<TimeConverter> converters, String defaultLocale) {
        this.converters = converters.stream()
                .collect(Collectors.toMap(TimeConverter::getSupportedLocale, Function.identity()));
        this.defaultLocale = defaultLocale;
    }

    public TimeConverter getConverter(String locale) {
        String localeKey = locale != null ? locale : defaultLocale;
        TimeConverter converter = converters.get(localeKey);
        
        if (converter == null) {
            throw new IllegalArgumentException("Unsupported locale: " + localeKey);
        }
        
        return converter;
    }

    public TimeConverter getDefaultConverter() {
        return getConverter(defaultLocale);
    }
}
