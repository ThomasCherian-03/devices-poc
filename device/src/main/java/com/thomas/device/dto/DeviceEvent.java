package com.thomas.device.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeviceEvent {
    private String empId;
    private String event;
    private String updatedBy;
    private String timestamp;
}
