package com.timeconverter.formatter;

import com.timeconverter.dto.TimeInput;

public interface MinuteHandler {

    boolean canHandle(int minute);

    String format(TimeInput timeInput);
}
