package com.thomas.device.constants;

public class DeviceConstants {

    private DeviceConstants()
    {

    }
    public static final String EMP_ID_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,10}$";
    public static final String EMP_ID_MESSAGE = "Employee ID must be 4-10 characters long and contain both letters and numbers";
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Device Registered successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "DELETE operation failed. Please try again or contact Dev team";

}
