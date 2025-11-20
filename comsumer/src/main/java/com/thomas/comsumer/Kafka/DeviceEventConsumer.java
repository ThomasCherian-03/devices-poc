package com.thomas.comsumer.Kafka;

import com.thomas.comsumer.dto.DeviceEventDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventConsumer {

    @KafkaListener(topics = "admin-update" ,groupId = "admin-update-group")
    public void updatedEventConsumer(DeviceEventDto deviceEventDto)
    {
        System.out.println("Update Event - " + deviceEventDto);
    }

    @KafkaListener(topics = "device-create" , groupId = "device-create-group")
    public void createdEventConsumer(DeviceEventDto deviceEventDto)
    {
        System.out.println("Created Event - " + deviceEventDto);
    }

}
