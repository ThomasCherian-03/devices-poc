package com.thomas.device.factory;

import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;
import com.thomas.device.mapper.DeviceMapper;
import com.thomas.device.util.DeviceUtils;
import org.springframework.stereotype.Component;

@Component
public class DeviceFactory implements IDeviceFactory {

    public Device create(DeviceDto deviceDto)
    {
        Device device = DeviceMapper.maptoDevice(deviceDto, new Device());

        device.setSerialNo(DeviceUtils.generateSerialNo());
        device.setMacAddress(DeviceUtils.generateMacAddress());
        device.setIpAddress(DeviceUtils.generateIpAddress());
        device.setManufacturer(DeviceUtils.getManufacturerName(deviceDto.getDeviceName()));

        return device;
    }
}
