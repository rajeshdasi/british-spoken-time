package com.timeconverter.factory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TimeConverter {

    private final Map<String, com.timeconverter.formatter.TimeConverter> converters;
    private final String defaultLocale;

    public TimeConverter(List<com.timeconverter.formatter.TimeConverter> converters, String defaultLocale) {
        this.converters = converters.stream()
                .collect(Collectors.toMap(com.timeconverter.formatter.TimeConverter::getSupportedLocale, Function.identity()));
        this.defaultLocale = defaultLocale;
    }

    public com.timeconverter.formatter.TimeConverter getConverter(String locale) {
        String localeKey = (locale != null && !locale.isBlank()) ? locale : defaultLocale;
        com.timeconverter.formatter.TimeConverter converter = converters.get(localeKey);

        if (converter == null) {
            throw new IllegalArgumentException("Unsupported locale: " + localeKey
                    + ". Supported locales: " + converters.keySet());
        }

        return converter;
    }

    public com.timeconverter.formatter.TimeConverter getDefaultConverter() {
        return getConverter(defaultLocale);
    }
}
