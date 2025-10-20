package com.timeconverter.formatter;

import com.timeconverter.model.TimeInput;

public interface MinuteHandler {

    boolean canHandle(int minute);

    String format(TimeInput timeInput);
}
