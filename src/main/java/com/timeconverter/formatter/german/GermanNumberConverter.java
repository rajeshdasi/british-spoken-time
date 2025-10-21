package com.timeconverter.formatter.german;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility component for converting numbers to German words.
 * Uses ResourceBundle for internationalization support.
 * Used by all German minute handlers to ensure consistency.
 */
@Component
public class GermanNumberConverter {

    private final ResourceBundle numberWords;

    public GermanNumberConverter() {
        this.numberWords = ResourceBundle.getBundle("numbers", Locale.forLanguageTag("de"));
    }

    /**
     * Converts hour (0-23) to German word representation.
     * Converts to 12-hour format (1-12).
     *
     * @param hour the hour value (0-23)
     * @return the German word representation of the hour
     */
    public String convertHourToWord(int hour) {
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }
        return convertNumberToWord(displayHour);
    }

    /**
     * Converts a number (1-30) to its German word representation.
     *
     * @param number the number to convert (1-30)
     * @return the German word representation of the number
     * @throws IllegalArgumentException if number is not in valid range
     */
    public String convertNumberToWord(int number) {
        if (number < 1 || number > 30) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }

        String key = "number." + number;
        if (!numberWords.containsKey(key)) {
            throw new IllegalArgumentException("No translation found for number: " + number);
        }

        return numberWords.getString(key);
    }
}
