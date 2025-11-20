package com.thomas.device.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;

    private String issuedBy;

    private String empId;

    private String serialNo;

    private String macAddress;

    private String ipAddress;

    private String locationName;

    private String areaName;

    private String manufacturer;

}
