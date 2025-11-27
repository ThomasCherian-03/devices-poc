package com.thomas.device.service.impl;


import com.thomas.device.dto.DeviceDto;
import com.thomas.device.entity.Device;
import com.thomas.device.factory.DeviceFactory;
import com.thomas.device.mapper.DeviceMapper;
import com.thomas.device.repository.DeviceRepository;
import com.thomas.device.service.IDeviceService;
import com.thomas.device.service.audit.AuditService;
import com.thomas.device.Kafka.event.KafkaDeviceEventPublisher;
import com.thomas.device.validator.DeviceValidator;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements IDeviceService {


    private final DeviceRepository deviceRepository;
    private final AuditService auditService;
    private final DeviceFactory deviceFactory;
    private final DeviceValidator deviceValidator;
    private final KafkaDeviceEventPublisher eventPublisher;


    /**
     *
     * @param deviceDto - Used to Register Device to the user
     */
    @Override
    public void createDevice(DeviceDto deviceDto) {

        deviceValidator.validateNewDevice(deviceDto);

        Device device = deviceFactory.create(deviceDto);

        deviceRepository.save(device);

        eventPublisher.publishDeviceCreate(deviceDto.getEmpId(),auditService.getLoggedInUser());

    }

    @Override
    @Cacheable(value = "devices", key = "#empId")
    public DeviceDto fetchDevice(String empId) {
        Device device = deviceValidator.validateExistingDevice(empId);
        DeviceDto deviceDto = DeviceMapper.maptoDeviceDto(device, new DeviceDto());
        return deviceDto;
    }

    @Override
    @CacheEvict(value = "devices", key = "#deviceDto.empId")
    public boolean updateDevice(DeviceDto deviceDto) {
        boolean isUpdated = false;
        if(deviceDto != null)
        {
            Device device = deviceValidator.validateExistingDevice(deviceDto.getEmpId());
            DeviceMapper.maptoDevice(deviceDto, device);
            deviceRepository.save(device);
            isUpdated = true;
        }
        eventPublisher.publishDeviceUpdate(deviceDto.getEmpId(),auditService.getLoggedInUser());
        return isUpdated;
    }

    @Override
    @CacheEvict(value = "devices", key="#empId")
    public boolean deleteDevice(String empId) {
        Device device = deviceValidator.validateExistingDevice(empId);
        deviceRepository.deleteByEmpId(device.getEmpId());
        return true;
    }
}
