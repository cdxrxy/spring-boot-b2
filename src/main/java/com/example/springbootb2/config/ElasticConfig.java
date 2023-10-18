package com.example.springbootb2.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("elastic")
@Setter
public class ElasticConfig {
    private String url;

    @Bean
    public RestClient restClient() {
        return RestClient.builder(HttpHost.create(url)).build();
    }

    @Bean
    public ElasticsearchTransport transport() {
        return new RestClientTransport(restClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient esClient() {
        return new ElasticsearchClient(transport());
    }
}
