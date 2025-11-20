package com.thomas.device.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data @AllArgsConstructor
public class ResponseDto  {

    private String statusCode;

    private String statusMsg;
}
