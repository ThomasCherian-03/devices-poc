package com.thomas.device.audit;

import com.thomas.device.service.impl.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
@AllArgsConstructor
public class AuditAwareImpl implements AuditorAware<String> {

    private final AuditService auditService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(auditService.getLoggedInUser());
    }
}
