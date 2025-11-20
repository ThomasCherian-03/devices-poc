package com.thomas.device.service.impl;

import com.thomas.device.Kafka.DeviceEventProducer;
import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;
import com.thomas.device.exception.DeviceAlreadyIssuedException;
import com.thomas.device.exception.ResourceNotFoundException;
import com.thomas.device.mapper.DeviceMapper;
import com.thomas.device.repository.DeviceRepository;
import com.thomas.device.service.IDeviceService;
import com.thomas.device.util.DeviceUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceEventProducer deviceEventProducer;

    private DeviceRepository deviceRepository;
    /**
     *
     * @param deviceDto - Used to Register Device to the user
     */
    @Override
//    @CachePut(value = "devices", key = "#deviceDto.empId")
    public void createDevice(DeviceDto deviceDto) {

        Device device = DeviceMapper.maptoDevice(deviceDto, new Device());

        Optional<Device> optionalEmpId = deviceRepository.findByEmpId(deviceDto.getEmpId());
        if(optionalEmpId.isPresent())
        {
            throw new DeviceAlreadyIssuedException("Device Was already issued by " +
                    deviceDto.getIssuedBy() + " Emp ID - " + deviceDto.getEmpId());
        }
        device.setSerialNo(DeviceUtils.generateSerialNo());
        device.setMacAddress(DeviceUtils.generateMacAddress());
        device.setIpAddress(DeviceUtils.generateIpAddress());
        device.setManufacturer(DeviceUtils.getManufacturerName(deviceDto.getDeviceName()));
        deviceRepository.save(device);

        String loggedInUser = getLoggedInUser();
        deviceEventProducer.triggerCreate(deviceDto.getEmpId(),loggedInUser);
    }

    @Override
    @Cacheable(value = "devices", key = "#empId")
    public DeviceDto fetchDevice(String empId) {
        Device device = deviceRepository.findByEmpId(empId).orElseThrow(
                ()-> new ResourceNotFoundException("Issued_By", "EmpId", empId)
        );
        DeviceDto deviceDto = DeviceMapper.maptoDeviceDto(device, new DeviceDto());
        return deviceDto;
    }

    @Override
    @CacheEvict(value = "devices", key = "#deviceDto.empId")
    public boolean updateDevice(DeviceDto deviceDto) {
        boolean isUpdated = false;
        if(deviceDto != null)
        {
            Device device = deviceRepository.findByEmpId(deviceDto.getEmpId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Device", "EmpId", deviceDto.getEmpId())
            );
            DeviceMapper.maptoDevice(deviceDto, device);
            deviceRepository.save(device);
            isUpdated = true;
        }

        String loggedInUser = getLoggedInUser();

        deviceEventProducer.triggerUpdate(deviceDto.getEmpId(),loggedInUser);
        return isUpdated;
    }

    @Override
    @CacheEvict(value = "devices", key="#empId")
    public boolean deleteDevice(String empId) {
        Device device = deviceRepository.findByEmpId(empId).orElseThrow(
                ()-> new ResourceNotFoundException("Device", "EmpId", empId)
        );
        deviceRepository.deleteByEmpId(device.getEmpId());
        return true;
    }

    private String getLoggedInUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return "System";
        }

        return authentication.getName();
    }

}
