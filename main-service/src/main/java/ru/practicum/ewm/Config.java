package ru.practicum.ewm;

import httpclient.StatsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${stats-server.url}")
    private String url;

    @Bean
    public StatsClient statsClient() {
        return new StatsClient(url);
    }


}
