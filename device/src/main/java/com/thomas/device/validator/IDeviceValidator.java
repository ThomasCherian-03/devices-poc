package com.thomas.device.validator;

import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;

public interface IDeviceValidator {

    void validateNewDevice(DeviceDto deviceDto);
    Device validateExistingDevice(String empId);
}
