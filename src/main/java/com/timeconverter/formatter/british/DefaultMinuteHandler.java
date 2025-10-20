package com.timeconverter.formatter.british;

import com.timeconverter.formatter.MinuteHandler;
import com.timeconverter.model.TimeInput;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Fallback handler for non-standard minutes above 30 (31-34, 36-39, 41-44, 46-49, 51-54, 56-59).
 * Format: "hour minute" (e.g., "ten thirty two", "eleven fifty seven").
 */
@Component
@BritishHandler
@Order(5)
public class DefaultMinuteHandler implements MinuteHandler {

    private final NumberToWordConverter numberConverter;

    public DefaultMinuteHandler(NumberToWordConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

    @Override
    public boolean canHandle(int minute) {
        return minute > 30 && minute < 60 
                && minute != 35 && minute != 40 && minute != 45 && minute != 50 && minute != 55;
    }

    @Override
    public String format(TimeInput timeInput) {
        String hourWord = numberConverter.convertHourToWord(timeInput.getHour());
        String minuteWord = numberConverter.convertMinuteToWord(timeInput.getMinute());
        return hourWord + " " + minuteWord;
    }
}
