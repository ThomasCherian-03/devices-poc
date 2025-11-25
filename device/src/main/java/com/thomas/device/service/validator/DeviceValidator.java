package com.thomas.device.service.validator;

import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;
import com.thomas.device.exception.DeviceAlreadyIssuedException;
import com.thomas.device.exception.ResourceNotFoundException;
import com.thomas.device.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DeviceValidator {

    private DeviceRepository deviceRepository;

    public void validateNewDevice(DeviceDto deviceDto)
    {
        Optional<Device> optionalEmpId = deviceRepository.findByEmpId(deviceDto.getEmpId());
        if(optionalEmpId.isPresent())
        {
            throw new DeviceAlreadyIssuedException("Device Was already issued by " +
                    deviceDto.getIssuedBy() + " Emp ID - " + deviceDto.getEmpId());
        }
    }

    public Device validateExistingDevice(String empId)
    {

        Device device = deviceRepository.findByEmpId(empId).orElseThrow(
            ()-> new ResourceNotFoundException("Device", "EmpId", empId)
        );
        return device;
    }



}
