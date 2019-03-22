package me.spring.boot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {


    @RequestMapping("today")
    public String getTodayWeather(HttpServletRequest request) {
        return "今天天气清理";
    }
}
