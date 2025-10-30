package com.timeconverter.service;

import com.timeconverter.factory.TimeConverter;
import com.timeconverter.dto.TimeInput;
import com.timeconverter.dto.TimeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TimeConversionServiceImpl implements TimeConversionService {

    private final TimeConverter converterFactory;

    public TimeConversionServiceImpl(TimeConverter converterFactory) {
        this.converterFactory = converterFactory;
    }

    @Override
    public Mono<TimeResponse> convertTime(String time, String locale) {
        return Mono.fromCallable(() -> {
            log.info("Converting time: {} for locale: {}", time, locale);

            TimeInput timeInput = TimeInput.parse(time);
            com.timeconverter.formatter.TimeConverter converter = converterFactory.getConverter(locale);
            String spokenTime = converter.convert(timeInput);

            log.info("Converted time: {} -> {}", time, spokenTime);

            return new TimeResponse(spokenTime, converter.getSupportedLocale());
        });
    }
}
