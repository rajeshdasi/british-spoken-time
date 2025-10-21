package com.timeconverter.formatter.british;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility component for converting numbers to British English words.
 * Demonstrates Single Responsibility Principle - one class for number conversion.
 * Uses ResourceBundle for internationalization support.
 * Used by all minute handlers to ensure consistency.
 */
@Component
public class NumberToWordConverter {

    private final ResourceBundle numberWords;

    public NumberToWordConverter() {
        this.numberWords = ResourceBundle.getBundle("numbers", Locale.forLanguageTag("en"));
    }

    /**
     * Converts hour (0-23) to word representation.
     * Converts to 12-hour format (1-12).
     *
     * @param hour the hour value (0-23)
     * @return the word representation of the hour
     */
    public String convertHourToWord(int hour) {
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }
        return convertNumberToWord(displayHour);
    }

    /**
     * Converts a number (1-59) to its word representation.
     *
     * @param number the number to convert (1-59)
     * @return the word representation of the number
     * @throws IllegalArgumentException if number is not in valid range
     */
    public String convertNumberToWord(int number) {
        if (number < 1 || number > 59) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }

        String key = "number." + number;
        if (!numberWords.containsKey(key)) {
            throw new IllegalArgumentException("No translation found for number: " + number);
        }

        return numberWords.getString(key);
    }

    /**
     * Converts minute to word representation.
     * Adds "o" prefix for single digit minutes (1-9).
     *
     * @param minute the minute value (1-59)
     * @return the word representation of the minute
     */
    public String convertMinuteToWord(int minute) {
        if (minute < 10) {
            return "o " + convertNumberToWord(minute);
        }
        return convertNumberToWord(minute);
    }
}
