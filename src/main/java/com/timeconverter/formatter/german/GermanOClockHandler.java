package com.timeconverter.formatter.german;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles o'clock times in German (XX:00).
 * Special cases: Mitternacht (midnight) and Mittag (noon).
 */
@Component
@GermanHandler
@Order(10)
public class GermanOClockHandler implements MinuteHandler {

    private final GermanNumberConverter numberConverter;

    public GermanOClockHandler(GermanNumberConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute == 0;
    }

    @Override
    public String format(TimeInput timeInput) {
        if (timeInput.getHour() == 0 && timeInput.getMinute() == 0) {
            return "Mitternacht";
        }
        if (timeInput.getHour() == 12 && timeInput.getMinute() == 0) {
            return "Mittag";
        }
        return numberConverter.convertHourToWord(timeInput.getHour()) + " Uhr";
    }
}
