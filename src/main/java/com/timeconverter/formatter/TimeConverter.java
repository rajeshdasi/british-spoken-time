package com.timeconverter.formatter;

import com.timeconverter.model.TimeInput;

public interface TimeConverter {

    String convert(TimeInput timeInput);

    String getSupportedLocale();
}
