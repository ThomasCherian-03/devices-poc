package com.thomas.device.controller;

import com.thomas.device.constants.DeviceConstants;
import com.thomas.device.dto.DeviceDto;
import com.thomas.device.dto.ResponseDto;
import com.thomas.device.service.IDeviceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class DeviceController {

    private IDeviceService iDeviceService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDevice(@Valid @RequestBody DeviceDto deviceDto)
    {
        iDeviceService.createDevice(deviceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DeviceConstants.STATUS_201, DeviceConstants.MESSAGE_201));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/fetch")
    public ResponseEntity<DeviceDto> fetchDeviceDetail(@RequestParam
                                   @Pattern(regexp = DeviceConstants.EMP_ID_PATTERN,message = DeviceConstants.EMP_ID_MESSAGE)
                                   String empId)
    {
        DeviceDto deviceDto = iDeviceService.fetchDevice(empId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deviceDto);

    }

    // just checking if updated by changes or not so allowed both
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDevice(@Valid @RequestBody DeviceDto deviceDto)
    {
        boolean isUpdated = iDeviceService.updateDevice(deviceDto);
        if(isUpdated)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(DeviceConstants.STATUS_200,DeviceConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(DeviceConstants.STATUS_417,DeviceConstants.MESSAGE_417_DELETE));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDevice(@RequestParam
                                                        @Pattern(regexp = DeviceConstants.EMP_ID_PATTERN, message = DeviceConstants.EMP_ID_MESSAGE)
                                                        String empId)
    {
        boolean isDeleted = iDeviceService.deleteDevice(empId);
        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(DeviceConstants.STATUS_200,DeviceConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(DeviceConstants.STATUS_417,DeviceConstants.MESSAGE_417_UPDATE));
        }
    }


}
