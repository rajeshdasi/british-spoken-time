package com.timeconverter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeInput {
    private int hour;
    private int minute;

    public static TimeInput parse(String time) {
        if (time == null || !time.matches("\\d{1,2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid time format. Expected HH:mm or H:mm");
        }

        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23");
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be between 0 and 59");
        }

        return new TimeInput(hour, minute);
    }
}
