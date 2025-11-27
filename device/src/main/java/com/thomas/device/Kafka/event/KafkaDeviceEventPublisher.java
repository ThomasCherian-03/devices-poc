package com.thomas.device.Kafka.event;

import com.thomas.device.Kafka.DeviceEventProducer;
import com.thomas.device.Kafka.IKafkaDeviceEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaDeviceEventPublisher implements IKafkaDeviceEventProducer {

    private final DeviceEventProducer deviceEventProducer;

    @Override
    public void publishDeviceCreate(String empId, String updatedBy) {
        deviceEventProducer.sendCreateEvent(empId,updatedBy);
    }

    @Override
    public void publishDeviceUpdate(String empId, String updatedBy) {
        deviceEventProducer.sendUpdateEvent(empId, updatedBy);
    }
}
