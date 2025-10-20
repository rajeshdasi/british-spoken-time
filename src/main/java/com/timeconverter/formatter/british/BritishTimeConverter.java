package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.formatter.TimeConverter;
import com.timeconverter.model.TimeInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("britishTimeConverter")
public class BritishTimeConverter implements TimeConverter {

    private final List<MinuteHandler> minuteHandlers;

    public BritishTimeConverter(@BritishHandler List<MinuteHandler> minuteHandlers) {
        this.minuteHandlers = minuteHandlers;
    }

    @Override
    public String convert(TimeInput timeInput) {
        return minuteHandlers.stream()
                .filter(handler -> handler.canHandle(timeInput.getMinute()))
                .findFirst()
                .map(handler -> handler.format(timeInput))
                .orElseThrow(() -> new IllegalStateException("No handler found for minute: " 
                        + timeInput.getMinute()));
    }

    @Override
    public String getSupportedLocale() {
        return "en_GB";
    }
}
