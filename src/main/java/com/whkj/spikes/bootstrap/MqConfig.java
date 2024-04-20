package com.whkj.spikes.bootstrap;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "http.mq")
public class MqConfig {

    private String host;

    private String appKey;

    private String secretKey;
}
