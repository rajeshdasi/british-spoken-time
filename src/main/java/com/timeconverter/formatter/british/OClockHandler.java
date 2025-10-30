package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.dto.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles o'clock times (XX:00).
 * Special cases: midnight (00:00) and noon (12:00).
 * Demonstrates Constructor Injection for shared utilities.
 */
@Component
@BritishHandler
@Order(1)
public class OClockHandler implements MinuteHandler {

    private final NumberToWordConverter numberConverter;

    public OClockHandler(NumberToWordConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute == 0;
    }

    @Override
    public String format(TimeInput timeInput) {
        if (timeInput.getHour() == 0 && timeInput.getMinute() == 0) {
            return "midnight";
        }
        if (timeInput.getHour() == 12 && timeInput.getMinute() == 0) {
            return "noon";
        }
        return numberConverter.convertHourToWord(timeInput.getHour()) + " o'clock";
    }
}
