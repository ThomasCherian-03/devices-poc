package com.thomas.device.factory;

import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;

public interface IDeviceFactory {
    Device create(DeviceDto deviceDto);
}
