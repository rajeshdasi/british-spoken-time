package com.timeconverter.service;

import com.timeconverter.exception.InvalidTimeFormatException;
import com.timeconverter.factory.TimeConverterFactory;
import com.timeconverter.formatter.TimeConverter;
import com.timeconverter.model.TimeInput;
import com.timeconverter.model.TimeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeConversionServiceImpl implements TimeConversionService {

    private final TimeConverterFactory converterFactory;

    public TimeConversionServiceImpl(TimeConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    @Override
    public TimeResponse convertTime(String time, String locale) {
        try {
            log.info("Converting time: {} for locale: {}", time, locale);
            
            TimeInput timeInput = TimeInput.parse(time);
            TimeConverter converter = converterFactory.getConverter(locale);
            String spokenTime = converter.convert(timeInput);
            
            log.info("Converted time: {} -> {}", time, spokenTime);
            
            return new TimeResponse(spokenTime, converter.getSupportedLocale());
        } catch (IllegalArgumentException e) {
            log.error("Invalid time format: {}", time, e);
            throw new InvalidTimeFormatException("Invalid time format: " + time, e);
        }
    }
}
