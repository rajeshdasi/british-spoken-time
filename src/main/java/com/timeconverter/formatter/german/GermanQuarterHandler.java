package com.timeconverter.formatter.german;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles quarter past and quarter to times in German.
 * 15 minutes: "Viertel nach" (quarter past)
 * 45 minutes: "Viertel vor" (quarter to)
 */
@Component
@GermanHandler
@Order(12)
public class GermanQuarterHandler implements MinuteHandler {

    private final GermanNumberConverter numberConverter;

    public GermanQuarterHandler(GermanNumberConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute == 15 || minute == 45;
    }

    @Override
    public String format(TimeInput timeInput) {
        if (timeInput.getMinute() == 15) {
            return "Viertel nach " + numberConverter.convertHourToWord(timeInput.getHour());
        } else {
            int nextHour = (timeInput.getHour() + 1) % 24;
            return "Viertel vor " + numberConverter.convertHourToWord(nextHour);
        }
    }
}
