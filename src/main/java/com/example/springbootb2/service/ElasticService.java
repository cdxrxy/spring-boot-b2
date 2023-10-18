package com.example.springbootb2.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ElasticService {
    private final ElasticsearchClient esClient;

    @SneakyThrows
    public double calculateGrowthRate() {
        Query currentMonthQuery = RangeQuery.of(m -> {
            RangeQuery.Builder builder = m.field("@timestamp");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            builder.gte(JsonData.of(
                    LocalDateTime.ofInstant(
                                    calendar.getTime().toInstant(), Clock.systemDefaultZone().getZone())
                            .toString()));

            calendar.add(Calendar.MONTH, 1);

            builder.lt(JsonData.of(
                    LocalDateTime.ofInstant(
                                    calendar.getTime().toInstant(), Clock.systemDefaultZone().getZone())
                            .toString()));

            return builder;
        })._toQuery();

        SearchResponse<?> currentMonthResponse = esClient.search(s ->
                s.index("nginx")
                        .query(q -> q.bool(b ->
                                b.must(currentMonthQuery))), Object.class);

        long currentMonthHits = currentMonthResponse.hits().total().value();

        Query lastMonthQuery = RangeQuery.of(m -> {
            RangeQuery.Builder builder = m.field("@timestamp");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.MONTH, -1);

            builder.gte(JsonData.of(
                    LocalDateTime.ofInstant(
                                    calendar.getTime().toInstant(), Clock.systemDefaultZone().getZone())
                            .toString()));

            calendar.add(Calendar.MONTH, 1);

            builder.lt(JsonData.of(
                    LocalDateTime.ofInstant(
                                    calendar.getTime().toInstant(), Clock.systemDefaultZone().getZone())
                            .toString()));

            return builder;
        })._toQuery();

        SearchResponse<?> lastMonthResponse = esClient.search(s ->
                s.index("nginx")
                        .query(q -> q.bool(b ->
                                b.must(lastMonthQuery))), Object.class);

        long lastMonthHits = lastMonthResponse.hits().total().value();

        return (double) (currentMonthHits - lastMonthHits) / lastMonthHits * 100;
    }

    @SneakyThrows
    public Map<Integer, Double> predictTraffic(double growthRate) {
        Map<Integer, Double> predictions = new HashMap<>();

        double currentTraffic = (double) esClient.search(s -> s.index("nginx"), Object.class)
                .hits().total().value();

        for (int i = 0; i < 6; i++) {
            currentTraffic += currentTraffic * growthRate / 100;
            predictions.put(i + 1, currentTraffic);
        }

        return predictions;
    }

    public String getScaleThreshold() {
        double predictedTraffic = predictTrafficSingle(calculateGrowthRate());

        int baseRPM = 1000;
        double scaleUpThreshold = 0.8;
        double scaleDownThreshold = 0.5;

        double scaleUp = baseRPM * scaleUpThreshold;
        double scaleDown = baseRPM * scaleDownThreshold;

        if (predictedTraffic > scaleUp) {
            return "Scale Up";
        }

        if (predictedTraffic < scaleDown) {
            return "Scale Down";
        }

        return "Maintain Current Infrastructure";
    }

    @SneakyThrows
    private double predictTrafficSingle(double growthRate) {
        double currentTraffic = (double) esClient.search(s -> s.index("nginx"), Object.class)
                .hits().total().value();

        return currentTraffic + (currentTraffic * growthRate / 100);
    }
}
