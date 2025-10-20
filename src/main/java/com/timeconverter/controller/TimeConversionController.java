package com.timeconverter.controller;

import com.timeconverter.model.TimeResponse;
import com.timeconverter.service.TimeConversionService;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/time")
public class TimeConversionController {

    private final TimeConversionService conversionService;

    public TimeConversionController(TimeConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/convert")
    public Mono<TimeResponse> convertTime(
            @RequestParam @NotBlank(message = "Time parameter cannot be blank") String time,
            @RequestParam(required = false) String locale) {

        log.info("Received request to convert time: {} with locale: {}", time, locale);
        return conversionService.convertTime(time, locale)
                .doOnSuccess(response -> log.info("Converted time: {} -> {}", time, response.getSpokenTime()));
    }
}
