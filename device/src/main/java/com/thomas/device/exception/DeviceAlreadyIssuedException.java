package com.thomas.device.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeviceAlreadyIssuedException extends RuntimeException{
    public DeviceAlreadyIssuedException(String message)
    {
        super(message);
    }

}
