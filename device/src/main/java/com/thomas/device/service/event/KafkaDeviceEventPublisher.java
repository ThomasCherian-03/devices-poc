package com.thomas.device.service.event;

import com.thomas.device.Kafka.DeviceEventProducer;
import com.thomas.device.service.IKafkaDeviceEventProducer;
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
