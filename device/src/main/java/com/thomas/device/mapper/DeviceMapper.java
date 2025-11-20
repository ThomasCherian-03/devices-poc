package com.thomas.device.mapper;

import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;

public class DeviceMapper {

    public static DeviceDto maptoDeviceDto(Device device, DeviceDto deviceDto)
    {
        deviceDto.setDeviceName(device.getDeviceName());
        deviceDto.setIssuedBy(device.getIssuedBy());
        deviceDto.setEmpId(device.getEmpId());
        deviceDto.setSerialNo(device.getSerialNo());
        deviceDto.setMacAddress(device.getMacAddress());
        deviceDto.setIpAddress(device.getIpAddress());
        deviceDto.setLocationName(device.getLocationName());
        deviceDto.setAreaName(device.getAreaName());
        deviceDto.setManufacturer(device.getManufacturer());
        return deviceDto;
    }

    public static Device maptoDevice(DeviceDto deviceDto, Device device)
    {
        // if ahead i want some field to be autocreated then i will remove from ehre
        device.setDeviceName(deviceDto.getDeviceName());
        device.setIssuedBy(deviceDto.getIssuedBy());
        device.setEmpId(deviceDto.getEmpId());
//        device.setSerialNo(deviceDto.getSerialNo());
//        device.setMacAddress(deviceDto.getMacAddress());
//        device.setIpAddress(deviceDto.getIpAddress());
        device.setLocationName(deviceDto.getLocationName());
        device.setAreaName(deviceDto.getAreaName());
//        device.setManufacturer(deviceDto.getManufacturer());
        return device;
    }
}
