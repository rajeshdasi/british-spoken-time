package com.timeconverter.service;

import com.timeconverter.model.TimeResponse;

public interface TimeConversionService {

    TimeResponse convertTime(String time, String locale);
}
