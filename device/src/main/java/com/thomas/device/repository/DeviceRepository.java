package com.thomas.device.repository;

import com.thomas.device.entity.Device;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByEmpId(String empId);

    @Transactional
    @Modifying
    void deleteByEmpId(String empId);
}
