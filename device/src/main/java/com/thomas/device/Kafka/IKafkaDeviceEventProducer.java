package com.thomas.device.Kafka;

public interface IKafkaDeviceEventProducer {

    void publishDeviceCreate(String empId,String updatedBy);
    void publishDeviceUpdate(String empId,String updatedBy);
}
