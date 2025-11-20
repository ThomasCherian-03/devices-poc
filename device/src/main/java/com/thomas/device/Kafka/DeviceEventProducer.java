/*
 DOING THIS FROM SPRING KAFKA INSTEAD OF USING cloud-stream-binder-kafka
 old code is commented down below
 */
package com.thomas.device.Kafka;

import com.thomas.device.dto.DeviceEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeviceEventProducer {

    private final KafkaTemplate<String, DeviceEventDto> kafkaTemplate;

    public DeviceEventProducer(KafkaTemplate<String, DeviceEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreateEvent(String empId, String updatedBy)
    {
        DeviceEventDto eventCreate = new DeviceEventDto(empId," Created ",updatedBy, LocalDateTime.now().toString());
        kafkaTemplate.send("device-create",empId,eventCreate);
    }

    public void sendUpdateEvent(String empId, String updatedBy)
    {
        DeviceEventDto eventUpdate = new DeviceEventDto(empId," Updated ",updatedBy, LocalDateTime.now().toString());
        kafkaTemplate.send("admin-update",empId,eventUpdate);
    }
}


//package com.thomas.device.Kafka;
//
//import com.thomas.device.dto.DeviceEventDto;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.function.Supplier;
//
//@Component
//public class DeviceEventProducer {
//
//    private volatile DeviceEventDto createEvent = null;
//    private volatile DeviceEventDto updateEvent = null;
//
//    public void triggerCreate(String empId, String updatedBy) {
//        this.createEvent = new DeviceEventDto(
//                empId,
//                "CREATE",
//                updatedBy,
//                LocalDateTime.now().toString()
//        );
//    }
//
//    public void triggerUpdate(String empId, String updatedBy) {
//        this.updateEvent = new DeviceEventDto(
//                empId,
//                "UPDATE",
//                updatedBy,
//                LocalDateTime.now().toString()
//        );
//    }
//
//    @Bean
//    public Supplier<DeviceEventDto> deviceUpdateEventSupplier() {
//        return () -> {
//            if (updateEvent != null) {
//                DeviceEventDto event = updateEvent;
//                updateEvent = null;
//                return event;
//            }
//            return null;
//        };
//    }
//
//    @Bean
//    public Supplier<DeviceEventDto> deviceCreateEventSupplier() {
//        return () -> {
//            if (createEvent != null) {
//                DeviceEventDto event = createEvent;
//                createEvent = null;
//                return event;
//            }
//
//            return null;
//        };
//    }
//
//}
