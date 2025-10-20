package com.timeconverter.controller;

import com.timeconverter.model.TimeResponse;
import com.timeconverter.service.TimeConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/time")
public class TimeConversionController {

    private final TimeConversionService conversionService;

    public TimeConversionController(TimeConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/convert")
    public ResponseEntity<TimeResponse> convertTime(
            @RequestParam String time,
            @RequestParam(required = false) String locale) {
        
        log.info("Received request to convert time: {} with locale: {}", time, locale);
        TimeResponse response = conversionService.convertTime(time, locale);
        return ResponseEntity.ok(response);
    }
}
