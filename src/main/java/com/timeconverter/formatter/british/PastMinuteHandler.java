package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles standard past times (5, 10, 15, 20, 25, 30 minutes past the hour).
 * Uses injected NumberToWordConverter for consistency.
 */
@Component
@BritishHandler
@Order(2)
public class PastMinuteHandler implements MinuteHandler {

    private final NumberToWordConverter numberConverter;

    public PastMinuteHandler(NumberToWordConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute > 0 && minute <= 30;
    }

    @Override
    public String format(TimeInput timeInput) {
        int minute = timeInput.getMinute();
        String minutePhrase = getMinutePhrase(minute);
        String hourWord = numberConverter.convertHourToWord(timeInput.getHour());
        return minutePhrase + " " + hourWord;
    }

    private String getMinutePhrase(int minute) {
        return switch (minute) {
            case 5 -> "five past";
            case 10 -> "ten past";
            case 15 -> "quarter past";
            case 20 -> "twenty past";
            case 25 -> "twenty five past";
            case 30 -> "half past";
            default -> numberConverter.convertMinuteToWord(minute);
        };
    }
}
