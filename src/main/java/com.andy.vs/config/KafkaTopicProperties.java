package com.andy.vs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "kafka.topic")
@Data
public class KafkaTopicProperties implements Serializable {
    private String groupId;
    private String[] topicName;
}
