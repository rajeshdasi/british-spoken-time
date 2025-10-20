package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles standard "to" times (35, 40, 45, 50, 55 minutes - five to, quarter to, etc.).
 * References the next hour rather than current hour.
 */
@Component
@BritishHandler
@Order(3)
public class ToMinuteHandler implements MinuteHandler {

    private final NumberToWordConverter numberConverter;

    public ToMinuteHandler(NumberToWordConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute >= 31 && (minute == 35 || minute == 40 || minute == 45 || minute == 50 || minute == 55);
    }

    @Override
    public String format(TimeInput timeInput) {
        int minute = timeInput.getMinute();
        int minutesTo = 60 - minute;
        String minutePhrase = getMinutePhrase(minutesTo);
        String nextHourWord = numberConverter.convertHourToWord((timeInput.getHour() + 1) % 24);
        return minutePhrase + " " + nextHourWord;
    }

    private String getMinutePhrase(int minutesTo) {
        return switch (minutesTo) {
            case 5 -> "five to";
            case 10 -> "ten to";
            case 15 -> "quarter to";
            case 20 -> "twenty to";
            case 25 -> "twenty five to";
            default -> throw new IllegalArgumentException("Invalid minutes to: " + minutesTo);
        };
    }
}
