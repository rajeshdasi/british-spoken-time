package com.timeconverter.formatter.german;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles all other minute values in German.
 * 1-29 minutes: "X Minuten nach Y" (X minutes past Y)
 * 31-44, 46-59 minutes: "X Minuten vor Y" (X minutes to Y)
 */
@Component
@GermanHandler
@Order(13)
public class GermanMinutesHandler implements MinuteHandler {

    private final GermanNumberConverter numberConverter;

    public GermanMinutesHandler(GermanNumberConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute > 0 && minute < 60 && minute != 15 && minute != 30 && minute != 45;
    }

    @Override
    public String format(TimeInput timeInput) {
        int minute = timeInput.getMinute();
        
        if (minute < 30) {
            String minuteWord = numberConverter.convertNumberToWord(minute);
            String hourWord = numberConverter.convertHourToWord(timeInput.getHour());
            return minuteWord + " Minuten nach " + hourWord;
        } else {
            int minutesToNext = 60 - minute;
            String minuteWord = numberConverter.convertNumberToWord(minutesToNext);
            int nextHour = (timeInput.getHour() + 1) % 24;
            String hourWord = numberConverter.convertHourToWord(nextHour);
            return minuteWord + " Minuten vor " + hourWord;
        }
    }
}
