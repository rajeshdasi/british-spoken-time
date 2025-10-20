package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles non-standard minutes between 0 and 30 (1-4, 6-9, 11-14, 16-19, 21-24, 26-29).
 * Format: "hour minute" (e.g., "six o three", "seven twenty one").
 */
@Component
@BritishHandler
@Order(4)
public class BetweenZeroAndThirtyHandler implements MinuteHandler {

    private final NumberToWordConverter numberConverter;

    public BetweenZeroAndThirtyHandler(NumberToWordConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute > 0 && minute < 30 
                && minute != 5 && minute != 10 && minute != 15 && minute != 20 && minute != 25;
    }

    @Override
    public String format(TimeInput timeInput) {
        String hourWord = numberConverter.convertHourToWord(timeInput.getHour());
        String minuteWord = numberConverter.convertMinuteToWord(timeInput.getMinute());
        return hourWord + " " + minuteWord;
    }
}
