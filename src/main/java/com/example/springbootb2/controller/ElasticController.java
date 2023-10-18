package com.example.springbootb2.controller;

import com.example.springbootb2.service.ElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class ElasticController {
    private final ElasticService elasticService;

    @GetMapping("/growth")
    public double calculateGrowthRate() {
        return elasticService.calculateGrowthRate();
    }

    @GetMapping("/traffic")
    public Map<Integer, Double> predictTraffic() {
        return elasticService.predictTraffic(elasticService.calculateGrowthRate());
    }

    @GetMapping("/scale")
    @ResponseBody
    public String getScaleThreshold() {
        return elasticService.getScaleThreshold();
    }
}