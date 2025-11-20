package com.thomas.device.util;

import java.util.Random;
import java.util.UUID;

public class DeviceUtils {
    public static String generateSerialNo() {
        return  UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateMacAddress() {
        Random random = new Random();
        StringBuilder mac = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            mac.append(String.format("%02X", random.nextInt(256)));
            if (i < 5) {
                mac.append(":");
            }
        }
        return mac.toString();
    }

    public static String generateIpAddress() {
        Random random = new Random();
        return random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256);
    }

    public static String getManufacturerName(String deviceName) {
        if (deviceName == null) {
            return "Unknown Manufacturer";
        }
        deviceName = deviceName.toLowerCase();

        if (deviceName.contains("laptop")) return "Dell";
        if (deviceName.contains("mobile")) return "Samsung";
        if (deviceName.contains("tablet")) return "Apple";
        if (deviceName.contains("printer")) return "HP";
        return "Generic Manufacturer";
    }
}
