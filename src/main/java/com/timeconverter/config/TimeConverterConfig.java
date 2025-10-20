package com.timeconverter.config;

import com.timeconverter.factory.TimeConverterFactory;
import com.timeconverter.formatter.TimeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for time conversion components.
 * Demonstrates Spring best practices:
 * - Component scanning for auto-discovery
 * - Constructor injection
 * - Property-based configuration
 * - Factory pattern with dependency injection
 */
@Configuration
@ComponentScan(basePackages = "com.timeconverter.formatter")
public class TimeConverterConfig {

    @Value("${time.converter.default.locale}")
    private String defaultLocale;

    /**
     * Creates TimeConverterFactory with auto-injected converters.
     * Spring automatically discovers all TimeConverter beans and injects them.
     * 
     * @param converters All TimeConverter implementations discovered by Spring
     * @return Configured factory instance
     */
    @Bean
    public TimeConverterFactory timeConverterFactory(List<TimeConverter> converters) {
        return new TimeConverterFactory(converters, defaultLocale);
    }
}
