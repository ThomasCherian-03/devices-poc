package com.thomas.device.service.audit;

import com.thomas.device.service.IAuditService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditService implements IAuditService {

    public String getLoggedInUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return "System";
        }

        return authentication.getName();
    }
}
