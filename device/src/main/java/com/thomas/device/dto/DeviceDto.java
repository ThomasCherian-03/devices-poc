package com.thomas.device.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto implements Serializable {

    @NotEmpty(message = "Device Name should not be empty")
    @Size(min = 5, max = 30 , message = "The length of device name should be in between 5 and 30")
    private String deviceName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 30 , message = "The length of name should be in between 5 and 30")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Name should only contain alphabets and spaces"
    )
    private String issuedBy;

    @NotEmpty(message = "employee ID should not be empty")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,10}$",
            message = "Employee ID must be 4-10 characters long and contain both letters and numbers"
    )
    private String empId;


    private String serialNo;

    private String macAddress;

    private String ipAddress;

    @NotEmpty(message = "Location Name should not be empty")
    private String locationName;

    @NotEmpty(message = "Area Name should not be empty")
    private String areaName;

    private String manufacturer;

}
