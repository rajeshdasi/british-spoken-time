package com.timeconverter.formatter.german;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.formatter.TimeConverter;
import com.timeconverter.model.TimeInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * German time converter implementation.
 * Converts time to German spoken format.
 */
@Component
@Qualifier("germanTimeConverter")
public class GermanTimeConverter implements TimeConverter {

    private final List<MinuteHandler> minuteHandlers;

    public GermanTimeConverter(@GermanHandler List<MinuteHandler> minuteHandlers) {
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
        return "de_DE";
    }
}
