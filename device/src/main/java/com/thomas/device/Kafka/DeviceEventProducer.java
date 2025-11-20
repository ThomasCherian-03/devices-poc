package com.thomas.device.Kafka;

import com.thomas.device.dto.DeviceEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
public class DeviceEventProducer {

    private volatile DeviceEvent createEvent = null;
    private volatile DeviceEvent updateEvent = null;

    public void triggerCreate(String empId, String updatedBy) {
        this.createEvent = new DeviceEvent(
                empId,
                "CREATE",
                updatedBy,
                LocalDateTime.now().toString()
        );
    }

    public void triggerUpdate(String empId, String updatedBy) {
        this.updateEvent = new DeviceEvent(
                empId,
                "UPDATE",
                updatedBy,
                LocalDateTime.now().toString()
        );
    }

    @Bean
    public Supplier<DeviceEvent> deviceUpdateEventSupplier() {
        return () -> {
            if (updateEvent != null) {
                DeviceEvent event = updateEvent;
                updateEvent = null;
                return event;
            }
            return null;
        };
    }

    @Bean
    public Supplier<DeviceEvent> deviceCreateEventSupplier() {
        return () -> {
            if (createEvent != null) {
                DeviceEvent event = createEvent;
                createEvent = null;
                return event;
            }

            return null;
        };
    }

}
