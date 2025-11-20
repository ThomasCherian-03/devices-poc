package com.thomas.device.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic adminUpdateTopic()
    {
        return new NewTopic("admin-update",3,(short) 1);
    }

    @Bean
    public NewTopic deviceCreateTopic() {
        return new NewTopic("device-create", 3, (short) 1);
    }
}
