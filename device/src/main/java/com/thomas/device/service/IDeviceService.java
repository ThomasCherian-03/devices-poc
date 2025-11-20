package com.thomas.device.service;

import com.thomas.device.dto.DeviceDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface IDeviceService {
    /**
     *
     * @param deviceDto - Used to Register Device to the user
     */
    void createDevice(DeviceDto deviceDto);

    /**
     *
     * @param empId - gets device by employee id
     * @return
     */
    DeviceDto fetchDevice(String empId);

    /**
     *
     * @param deviceDto - to update the Device
     * @return
     */
    boolean updateDevice(DeviceDto deviceDto);

    @Transactional
    @Modifying
    boolean deleteDevice(String empId);

}
