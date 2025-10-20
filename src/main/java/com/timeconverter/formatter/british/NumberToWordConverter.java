package com.timeconverter.formatter.british;

import org.springframework.stereotype.Component;

/**
 * Utility component for converting numbers to British English words.
 * Demonstrates Single Responsibility Principle - one class for number conversion.
 * Used by all minute handlers to ensure consistency.
 */
@Component
public class NumberToWordConverter {

    public String convertHourToWord(int hour) {
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }
        return convertNumberToWord(displayHour);
    }

    public String convertNumberToWord(int number) {
        return switch (number) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            case 10 -> "ten";
            case 11 -> "eleven";
            case 12 -> "twelve";
            case 13 -> "thirteen";
            case 14 -> "fourteen";
            case 15 -> "fifteen";
            case 16 -> "sixteen";
            case 17 -> "seventeen";
            case 18 -> "eighteen";
            case 19 -> "nineteen";
            case 20 -> "twenty";
            case 21 -> "twenty one";
            case 22 -> "twenty two";
            case 23 -> "twenty three";
            case 24 -> "twenty four";
            case 25 -> "twenty five";
            case 26 -> "twenty six";
            case 27 -> "twenty seven";
            case 28 -> "twenty eight";
            case 29 -> "twenty nine";
            case 30 -> "thirty";
            case 31 -> "thirty one";
            case 32 -> "thirty two";
            case 33 -> "thirty three";
            case 34 -> "thirty four";
            case 35 -> "thirty five";
            case 36 -> "thirty six";
            case 37 -> "thirty seven";
            case 38 -> "thirty eight";
            case 39 -> "thirty nine";
            case 40 -> "forty";
            case 41 -> "forty one";
            case 42 -> "forty two";
            case 43 -> "forty three";
            case 44 -> "forty four";
            case 45 -> "forty five";
            case 46 -> "forty six";
            case 47 -> "forty seven";
            case 48 -> "forty eight";
            case 49 -> "forty nine";
            case 50 -> "fifty";
            case 51 -> "fifty one";
            case 52 -> "fifty two";
            case 53 -> "fifty three";
            case 54 -> "fifty four";
            case 55 -> "fifty five";
            case 56 -> "fifty six";
            case 57 -> "fifty seven";
            case 58 -> "fifty eight";
            case 59 -> "fifty nine";
            default -> throw new IllegalArgumentException("Invalid number: " + number);
        };
    }

    public String convertMinuteToWord(int minute) {
        if (minute < 10) {
            return "o " + convertNumberToWord(minute);
        }
        return convertNumberToWord(minute);
    }
}
