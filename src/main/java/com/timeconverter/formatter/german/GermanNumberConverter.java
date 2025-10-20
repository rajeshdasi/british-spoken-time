package com.timeconverter.formatter.german;

import org.springframework.stereotype.Component;

/**
 * Utility component for converting numbers to German words.
 * Used by all German minute handlers to ensure consistency.
 */
@Component
public class GermanNumberConverter {

    public String convertHourToWord(int hour) {
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }
        return convertNumberToWord(displayHour);
    }

    public String convertNumberToWord(int number) {
        return switch (number) {
            case 1 -> "eins";
            case 2 -> "zwei";
            case 3 -> "drei";
            case 4 -> "vier";
            case 5 -> "fünf";
            case 6 -> "sechs";
            case 7 -> "sieben";
            case 8 -> "acht";
            case 9 -> "neun";
            case 10 -> "zehn";
            case 11 -> "elf";
            case 12 -> "zwölf";
            case 13 -> "dreizehn";
            case 14 -> "vierzehn";
            case 15 -> "fünfzehn";
            case 16 -> "sechzehn";
            case 17 -> "siebzehn";
            case 18 -> "achtzehn";
            case 19 -> "neunzehn";
            case 20 -> "zwanzig";
            case 21 -> "einundzwanzig";
            case 22 -> "zweiundzwanzig";
            case 23 -> "dreiundzwanzig";
            case 24 -> "vierundzwanzig";
            case 25 -> "fünfundzwanzig";
            case 26 -> "sechsundzwanzig";
            case 27 -> "siebenundzwanzig";
            case 28 -> "achtundzwanzig";
            case 29 -> "neunundzwanzig";
            case 30 -> "dreißig";
            default -> throw new IllegalArgumentException("Invalid number: " + number);
        };
    }
}
