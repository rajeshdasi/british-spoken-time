package com.timeconverter.service;

import com.timeconverter.dto.TimeResponse;
import reactor.core.publisher.Mono;

public interface TimeConversionService {

    Mono<TimeResponse> convertTime(String time, String locale);
}
