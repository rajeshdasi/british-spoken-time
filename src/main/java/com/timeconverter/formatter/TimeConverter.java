package com.timeconverter.formatter;

import com.timeconverter.dto.TimeInput;

public interface TimeConverter {

    String convert(TimeInput timeInput);

    String getSupportedLocale();
}
