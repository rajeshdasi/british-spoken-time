package com.timeconverter.formatter.german;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles half past times in German (XX:30).
 * In German, "halb" refers to the NEXT hour.
 * Example: 9:30 = "halb zehn" (half to ten).
 */
@Component
@GermanHandler
@Order(11)
public class GermanHalfHandler implements MinuteHandler {

    private final GermanNumberConverter numberConverter;

    public GermanHalfHandler(GermanNumberConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute == 30;
    }

    @Override
    public String format(TimeInput timeInput) {
        int nextHour = (timeInput.getHour() + 1) % 24;
        return "halb " + numberConverter.convertHourToWord(nextHour);
    }
}
